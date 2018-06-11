/**
 * Copies a BMP piece by piece to a scale of n
 */
       
#include <stdio.h>
#include <stdlib.h>

#include "bmp.h"

int main(int argc, char *argv[])
{
    // ensure proper usage
    if (argc != 4)
    {
        fprintf(stderr, "Usage: ./resize k infile outfile\n");
        return 1;
    }

    // remember filenames
    char *infile = argv[2];
    char *outfile = argv[3];

    int n = atoi(argv[1]);
    
    // open input file 
    FILE *inptr = fopen(infile, "r");
    if (inptr == NULL)
    {
        fprintf(stderr, "Could not open %s.\n", infile);
        return 2;
    }

    // open output file
    FILE *outptr = fopen(outfile, "w");
    if (outptr == NULL)
    {
        fclose(inptr);
        fprintf(stderr, "Could not create %s.\n", outfile);
        return 3;
    }

    // read infile's BITMAPFILEHEADER and save to bf and bf_cp
    BITMAPFILEHEADER bf, bf_cp;
    fread(&bf, sizeof(BITMAPFILEHEADER), 1, inptr);
    bf_cp = bf;

    // read infile's BITMAPINFOHEADER and save to bi and bi_cp
    BITMAPINFOHEADER bi, bi_cp;
    fread(&bi, sizeof(BITMAPINFOHEADER), 1, inptr);
    bi_cp = bi;

    // ensure infile is (likely) a 24-bit uncompressed BMP 4.0
    if (bf.bfType != 0x4d42 || bf.bfOffBits != 54 || bi.biSize != 40 || 
        bi.biBitCount != 24 || bi.biCompression != 0)
    {
        fclose(outptr);
        fclose(inptr);
        fprintf(stderr, "Unsupported file format.\n");
        return 4;
    }

    // alter values of bf and bi
    bi_cp.biWidth = bi.biWidth * n;
    bi_cp.biHeight = bi.biHeight * n;
    

    // determine padding for scanlines
    int padding = (4 - (bi.biWidth * sizeof(RGBTRIPLE)) % 4) % 4;
    int cpPadding = (4 - (bi_cp.biWidth * sizeof(RGBTRIPLE)) % 4 ) % 4;
    
    // alter sizes
    bi_cp.biSizeImage = (bi_cp.biWidth * sizeof(RGBTRIPLE) + cpPadding) * abs(bi_cp.biHeight);
    bf_cp.bfSize = sizeof(BITMAPFILEHEADER) + sizeof(BITMAPINFOHEADER) + bi_cp.biSizeImage;

    // write outfile's BITMAPFILEHEADER
    fwrite(&bf_cp, sizeof(BITMAPFILEHEADER), 1, outptr);

    // write outfile's BITMAPINFOHEADER
    fwrite(&bi_cp, sizeof(BITMAPINFOHEADER), 1, outptr);

    // iterate over infile's scanlines
    for (int i = 0, biHeight = abs(bi.biHeight); i < biHeight; i++)
    {
        
        // repeat scanline n times
        for (int factor = 0; factor < n; factor++)
        {
            // iterate over pixels in scanline
            for (int j = 0; j < bi.biWidth; j++)
            {
                // temporary storage
                RGBTRIPLE triple;
    
                // read RGB triple from infile
                fread(&triple, sizeof(RGBTRIPLE), 1, inptr);
    
                // write RGB triple to outfile n times
                for (int k = 0; k < n; k++)
                {
                    fwrite(&triple, sizeof(RGBTRIPLE), 1, outptr);
                }
            }
    
            // add padding to output
            for (int k = 0; k < cpPadding; k++)
            {
                fputc(0x00, outptr);
            }
            
            // state: output ready for next line, input is right before padding on the same line
            // go back the number bits that we just went if this loop will happen again
            if (factor + 1 < n)
            {
                for (int goBack = 0; goBack < bi.biWidth * sizeof(RGBTRIPLE); goBack++)
                {
                    fseek(inptr, -1, SEEK_CUR);
                }
            }
            
                
            // state: output ready for next line, input at beginning of same line if this loop will happen at least one more time
        }
        
        // skip over padding, if any
        fseek(inptr, padding, SEEK_CUR);
        
       
    }

    // close infile
    fclose(inptr);

    // close outfile
    fclose(outptr);

    // success
    return 0;
}

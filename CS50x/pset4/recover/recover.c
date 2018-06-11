/**
 * Copies a BMP piece by piece, just because.
 */
       
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdbool.h>
#include <string.h>


#define BLOCK_SIZE 512

// defining BYTE for the purposes of saving bytes in memory
typedef uint8_t  BYTE;


int main(int argc, char *argv[])
{
    
    // ensure proper usage
    if (argc != 2)
    {
        fprintf(stderr, "Usage: ./recover image\n");
        return 1;
    }

    FILE *infile = fopen(argv[1], "r");
    if (infile == NULL)
    {
        fprintf(stderr, "Could not open %s.\n", argv[1]);
        return 2;
    }
    
    // create output file
    FILE *outfile;

    // declare types that can be used in while loop
    int file_count = 0;
    bool fileIsOpen = false;
    BYTE block[512];

    while (fread(block, 512, 1, infile) > 0)
    {
        // if there is a jpeg at this block, then go through this
        if (block[0] == 0xff && block[1] == 0xd8 && block[2] == 0xff && (block[3] & 0xf0) == 0xe0)
        {
            // if a file is currently open, close it before creating a new jpeg
            if (fileIsOpen)
            {
                fclose(outfile);
            }
            
            // 4 is for the 3 digits of the filename and \0
            char* filename = malloc(4 * sizeof(char));
            sprintf(filename, "%0.03i.jpg", file_count);
            file_count++;
            
            // create the new outfile that will be compounded onto
            outfile = fopen(filename, "w");
            
            // because we used malloc, free memory to avoid leaks
            free(filename);
            
            // write the data in this block to the currently open outfile
            fwrite(block, sizeof(block), 1, outfile);
            
            // if it's not already, just make sure fileisopen is true
            fileIsOpen = true;
        } 
        else if(fileIsOpen) // otherwise, just write to the current file if a file is open
        {
            fwrite(block, sizeof(block), 1, outfile);
        }
    }
    
    // since the previous while loop broke because it reached the EOF, we need to close the current jpeg (if it exists!)
    if (fileIsOpen)
    {
        fclose(outfile);        
    }
    
    // almost done: close the infile
    fclose(infile);

    // wir sind fertig!
    return 0;
    
}

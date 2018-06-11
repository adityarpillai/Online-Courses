#include <cs50.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>

void print_encrypt(string shift, string plain);
int getShift(string shift, int j, int shiftLen);


int main(int argc, string argv[])
{
    string key;
    // make sure that there are 2 arguments
    if (argc == 2)
    {
        // capture and save the key from the CLI
        key = argv[1];
     
        // iterate through each char of argv[1] to ensure letters
        for (int i = 0, n = strlen(key); i < n; i++)
        {
            if (!isalpha(argv[1][i]))
            {
                // break out of main method and return if a char isn't alpha
                printf("Usage: ./vigenere k\n");
                return 1;
            }
        }
    }
    else
    {
        printf("Usage: ./vigenere k\n");
        return 1;
    }
    
    // request user for plaintext string to be encrypted
    printf("plaintext: ");
    string plain = get_string();
    
    // print converted string
    printf("ciphertext: ");
    print_encrypt(key, plain);
}

void print_encrypt(string shift, string plain)
{
    // set value of the shift
    int j = 0;
    int shiftLen = strlen(shift);
    
    // iterate through each char in the string plain
    for (int i = 0, n = strlen(plain); i < n; i++)
    {
        // check if a letter
        if (isalpha(plain[i]))
        {
            // check lower/upper case
            if (isupper(plain[i]))
            {
                // gets alphabet value of the char (ex: A = 0, B = 1, C = 2, ... , Z = 25)
                int alphaVal = plain[i] - 'A';
                
                // finds the value to add to 'A' by using c = (pi + kj) % 26
                int toAdd = (alphaVal + getShift(shift, j, shiftLen)) % 26;
                printf("%c", (char) ('A' + toAdd));
                j++;
            }
            else
            {
                // gets alphabet value of the char (ex: a = 0, b = 1, c = 2, ... , z = 25)
                int alphaVal = plain[i] - 'a';
                
                // finds the value to add to 'a' by using c = (p + k) % 26
                int toAdd = (alphaVal + getShift(shift, j, shiftLen)) % 26;
                printf("%c", ('a' + toAdd));
                j++;
            }
        }
        else
        {
            printf("%c", plain[i]);
        }
    }
    printf("\n");
}

int getShift(string shift, int j, int shiftLen)
{
    int index = j % shiftLen;
    if (islower(shift[index]))
    {
        return shift[index] - 'a';
    }
    return shift[index] - 'A';
}
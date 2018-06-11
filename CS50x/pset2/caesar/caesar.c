#include <cs50.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>

void print_encrypt(int shift, string plain);

int main(int argc, string argv[])
{
    int shift;
    // make sure that there are 2 arguments
    if (argc == 2)
    {
        // capture shift amount from command-line argument
        shift = atoi(argv[1]);
    }
    else
    {
        printf("Usage: ./caesar k\n");
        return 1;
    }
    
    // request user for plaintext string to be encrypted
    printf("plaintext: ");
    string plain = get_string();
    
    // print converted string
    printf("ciphertext: ");
    print_encrypt(shift, plain);
}

void print_encrypt(int shift, string plain)
{
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
                
                // finds the value to add to 'A' by using c = (p + k) % 26
                int toAdd = (alphaVal + shift) % 26;
                printf("%c", (char) ('A' + toAdd));
            }
            else
            {
                // gets alphabet value of the char (ex: a = 0, b = 1, c = 2, ... , z = 25)
                int alphaVal = plain[i] - 'a';
                
                // finds the value to add to 'a' by using c = (p + k) % 26
                int toAdd = (alphaVal + shift) % 26;
                printf("%c", ('a' + toAdd));
            }
        }
        else
        {
            printf("%c", plain[i]);
        }
    }
    printf("\n");
}
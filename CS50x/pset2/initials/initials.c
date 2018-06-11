#include <cs50.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>

void print_initials(string str);


int main(void)
{
    // get user's string as input
    string str = get_string();
    
    // check to make sure get_string() returned a string
    if (str != NULL)
    {
        // prints string
        print_initials(str);
        return 0;
    }
    
    // if str was null, returns 1 as an error code
    return 1;
}

// prints the capital letters of the parameter str
void print_initials(string str)
{
    // iterate through each character of str
    for (int i = 0, n = strlen(str); i < n; i++)
    {
        // if the character at index i is not a space and either it is the index = 0 or the character before is a space, then the 
        // character at index i is the initial. Thus, it should be printed as a capital char.
        if(str[i] != ' ' && (i == 0 || str[i - 1] == ' '))
        {
            printf("%c", toupper(str[i]));
        }
    }
    
    // print new line for clarity
    printf("\n");
}
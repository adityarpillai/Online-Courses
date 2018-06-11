#include <stdio.h>
#include <cs50.h>
#include <math.h>

int main(void)
{
    printf("Number: ");
    long long cc = get_long_long();
    
    int sum = 0;
    // create long long duplicate, and summate every value that isn't going to be mult by 2
    for (long long duplicate = cc; duplicate > 0; duplicate /= 100)
    {
        sum += duplicate % 10;
    }
    
    // then iterate through each of the values that are going to be doubled
    int doubled_sum = 0;
    for (long long duplicate = cc / 10; duplicate > 0; duplicate /= 100)
    {
        int digit = (duplicate % 10) * 2;
        
        // dissect each doubled digit
        while (digit > 0)
        {
            doubled_sum += digit % 10;
            digit /= 10;
        }
    }
    
    sum += doubled_sum;
    
    if (sum % 10 != 0)
    {
        printf("INVALID\n");
    }
    else 
    {
        // check for 16 digit mastercard
        if (cc >= 51 * ((long long) pow(10, 14)) && cc < 56 * ((long long) pow(10, 14)))
        {
            printf("MASTERCARD\n");
        } // next check for 15 digit AMEX
        else if (cc / ((long long) pow(10, 13)) == 34 || (cc / ((long long) pow(10, 13)) == 37))
        {
            printf("AMEX\n");
        } // next check for both 13 digit and 16 digit VISA cards
        else if (cc / ((long long) pow(10, 12)) == 4 || cc / ((long long) pow(10, 15)) == 4)
        {
            printf("VISA\n");
        }
        else
        {
            printf("INVALID\n");
        }
    }
}
/**
 * helpers.c
 *
 * Helper functions for Problem Set 3.
 */
 
#include <cs50.h>

#include "helpers.h"

#define LIMIT 65536

/**
 * Returns true if value is in array of n values, else false.
 */
bool search(int value, int values[], int n)
{
    // construct three variables for beginning, mid, and end of each search
    int beginning = 0;
    int mid = n / 2;
    
    // note: the end value is exclusive and that index won't be searched
    int end = n;
    
    // while loop to find value
    while (true)
    {
        // if the end is the same as the beginning or less than the beginning, then no value has been found in between
        if (end <= beginning)
        {
            break;
        }
        
        // 3 way comparison. is mid equal to value? is mid less than value? otherwise, mid is greather than value.
        if (values[mid] == value)
        {
            return true;
        }
        else if (values[mid] > value)
        {
            // search left & redefine the beginning, mid, and end of the array
            end = mid;
            mid = (end - beginning) / 2 + beginning;
        }
        else
        {
            // search right & redefine the beginning, mid, and end of the array
            beginning = mid + 1;
            mid = (end - beginning) / 2 + beginning;
        }
    }
    
    return false;
}

/**
 * Sorts array of n values.
 */
void sort(int values[], int n)
{
    // initialize all values of counts to 0 where counts has indices from 0 to LIMIT (inclusive)
    int counts[LIMIT + 1] = {0};
    
    // iterate through every int in values
    for (int i = 0; i < n; i++)
    {
        // increment the count at the index of the value
        counts[values[i]]++;
    }
    
    int valuesIndex = 0;
    
    // iterate through every element of counts
    for (int i = 0; i < LIMIT + 1; i++)
    {
        // add the value at the element, the element number of times, to values
        while (counts[i] > 0)
        {
            // eprintf("at counts[%i] = %i\n", i, counts[i]);
            values[valuesIndex] = i;
            counts[i]--;
            valuesIndex++;
        }
    }
    
    // success
    return;
}

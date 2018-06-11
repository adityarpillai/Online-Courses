#include <stdio.h>
#include <cs50.h>

int main(void) 
{
    printf("Minutes: ");
    float mins = get_float();
    printf("Bottles: %i\n", (int)((mins * 1.5 * 128) / 16));
}
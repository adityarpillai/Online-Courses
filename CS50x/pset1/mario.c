#include <stdio.h>
#include <cs50.h>

void print_pyramid(int height);
void print_left(int incr, int height);
void print_right(int incr);

int main(void) 
{
    printf("Height: ");
    int height = get_int();
    while(height < 0 || height > 23)
    {
        printf("Height: ");
        height = get_int();
    }
    print_pyramid(height);
}

void print_pyramid(int height)
{
    for (int i = 0; i < height; i++)
    {
        print_left(i, height);
        printf("%s", "  ");
        print_right(i);
        printf("\n");
    }
}

void print_left(int incr, int height)
{
    int blank = height - incr - 1;
    for (int i = 0; i < blank; i++)
    {
        printf("%s", " ");
    }
    int block = height - blank;
    for (int i = 0; i < block; i++)
    {
        printf("#");   
    }
}

void print_right(int incr)
{
    int block = incr + 1;
    for (int i = 0; i < block; i++)
    {
        printf("#");
    }
}


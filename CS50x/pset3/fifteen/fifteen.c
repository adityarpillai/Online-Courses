/**
 * fifteen.c
 *
 * Implements Game of Fifteen (generalized to d x d).
 *
 * Usage: fifteen d
 *
 * whereby the board's dimensions are to be d x d,
 * where d must be in [DIM_MIN,DIM_MAX]
 *
 * Note that usleep is obsolete, but it offers more granularity than
 * sleep and is simpler to use than nanosleep; `man usleep` for more.
 */
 
#define _XOPEN_SOURCE 500

#include <cs50.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

// constants
#define DIM_MIN 3
#define DIM_MAX 9

// board
int board[DIM_MAX][DIM_MAX];

// dimensions
int d;
int blank_r;
int blank_c;

// prototypes
int getVal(int r, int c);
void clear(void);
void greet(void);
void init(void);
void draw(void);
bool move(int tile);
bool won(void);
bool swap(int tr, int tc);

int main(int argc, string argv[])
{
    // ensure proper usage
    if (argc != 2)
    {
        printf("Usage: fifteen d\n");
        return 1;
    }

    // ensure valid dimensions
    d = atoi(argv[1]);
    if (d < DIM_MIN || d > DIM_MAX)
    {
        printf("Board must be between %i x %i and %i x %i, inclusive.\n",
            DIM_MIN, DIM_MIN, DIM_MAX, DIM_MAX);
        return 2;
    }

    // open log
    FILE *file = fopen("log.txt", "w");
    if (file == NULL)
    {
        return 3;
    }

    // greet user with instructions
    greet();

    // initialize the board
    init();

    // accept moves until game is won
    while (true)
    {
        // clear the screen
        clear();

        // draw the current state of the board
        draw();

        // log the current state of the board (for testing)
        for (int i = 0; i < d; i++)
        {
            for (int j = 0; j < d; j++)
            {
                fprintf(file, "%i", board[i][j]);
                if (j < d - 1)
                {
                    fprintf(file, "|");
                }
            }
            fprintf(file, "\n");
        }
        fflush(file);

        // check for win
        if (won())
        {
            printf("ftw!\n");
            break;
        }

        // prompt for move
        printf("Tile to move: ");
        int tile = get_int();
        
        // quit if user inputs 0 (for testing)
        if (tile == 0)
        {
            break;
        }

        // log move (for testing)
        fprintf(file, "%i\n", tile);
        fflush(file);

        // move if possible, else report illegality
        if (!move(tile))
        {
            printf("\nIllegal move.\n");
            usleep(500000);
        }

        // sleep thread for animation's sake
        usleep(500000);
    }
    
    // close log
    fclose(file);

    // success
    return 0;
}

/**
 * Clears screen using ANSI escape sequences.
 */
void clear(void)
{
    printf("\033[2J");
    printf("\033[%d;%dH", 0, 0);
}

/**
 * Greets player.
 */
void greet(void)
{
    clear();
    printf("WELCOME TO GAME OF FIFTEEN\n");
    usleep(2000000);
}

/**
 * Initializes the game's board with tiles numbered 1 through d*d - 1
 * (i.e., fills 2D array with values but does not actually print them).  
 */
void init(void)
{
    // iterate through each row
    for (int r = 0; r < d; r++)
    {
        // iterate through each col
        for (int c = 0; c < d; c++)
        {
            board[r][c] = (d * d) - 1 - getVal(r, c);
        }
    }
    
    // if even, swap 1 and 2
    if (d % 2 == 0)
    {
        board[d - 1][d - 3] = 1;
        board[d - 1][d - 2] = 2;
    }
    
    blank_r = d - 1;
    blank_c = d - 1;
}

int getVal(int r, int c)
{
    return r * d + c;
}

/**
 * Prints the board in its current state.
 */
void draw(void)
{
    // iterate through rows
    for (int r = 0; r < d; r++)
    {
        printf("\n");
        for (int c = 0; c < d; c++)
        {
            if (board[r][c] == 0)
            {
                printf("%3s", "_");
            }
            else
            {
                printf("%3i", board[r][c]);
            }
        }
        printf("\n");
    }
    printf("\n");
}

/**
 * If tile borders empty space, moves tile and returns true, else
 * returns false. 
 */
bool move(int tile)
{
    // check to see if tile is in valid range
    if (tile <= 0 || tile >= d * d)
    {
        return false;
    }
    
    // get row and col of the tile to move
    int tile_r = -1;
    int tile_c = -1;
    for (int r = 0; r < d; r++)
    {
        
        // break out of outer loop when the values are no longer sentinels
        if (tile_r != -1 || tile_c != -1)
        {
            break;
        }
        
        for (int c = 0; c < d; c++)
        {
            if (board[r][c] == tile)
            {
                tile_r = r;
                tile_c = c;
                break;
            }
        }
    }
    
    // check left (c - 1)
    if (tile_c - 1 == blank_c && tile_r == blank_r)
    {
        return swap(tile_r, tile_c);
    } // else check right (c + 1)
    else if (tile_c + 1 == blank_c && tile_r == blank_r)
    {
        return swap(tile_r, tile_c);
    } // else check up (r - 1)
    else if (tile_r - 1 == blank_r && tile_c == blank_c)
    {
        return swap(tile_r, tile_c);
    } // else check down
    else if (tile_r + 1 == blank_r && tile_c == blank_c)
    {
        return swap(tile_r, tile_c);
    }
    
    // otherwise, no valid move
    return false;
}

/**
 * swaps the value at (tr, tc) with the global variables (blank_r, blank_c) and returns true
 */
bool swap(int tr, int tc)
{
    // creates temporary integer
    int t = board[tr][tc];
    
    // swaps
    board[tr][tc] = board[blank_r][blank_c];
    board[blank_r][blank_c] = t;
    
    // change blank_r, blank_c values
    blank_r = tr;
    blank_c = tc;
    
    return true;
}

/**
 * Returns true if game is won (i.e., board is in winning configuration), 
 * else false.
 */
bool won(void)
{
    // iterate through each row
    for (int r = 0; r < d; r++)
    {
        // iterate through each col
        for (int c = 0; c < d; c++)
        {
            // if the value is the last piece [d-1][d-1], then return false if it isn't 0
            if (r == d - 1 && c == d - 1 && board[r][c] != 0)
            {
                // eprintf("returning false because board[%i][%i] = %i =/= 0", r, c, board[r][c]);
                return false;
            } // otherwise, if it isn't equal to getVal(r, c) + 1, then return false
            else if (r != d - 1 && c != d - 1 && getVal(r, c) + 1 != board[r][c])
            {
                // eprintf("returning false because getVal(%i, %i) + 1 = %i =/= %i = board[%i][%i]", r, c, getVal(r, c) + 1, board[r][c], r, c);
                // note: getVal(r, c) counts from 0, 1, 2, .... (d * d) where (d * d) is replaced with 0 at init, thus we must add 1
                // since the correct solution starts counts as 1, 2, 3 ....
                return false;
            }
        }
    }
    return true;
}

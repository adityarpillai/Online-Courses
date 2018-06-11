/**
 * Implements a dictionary's functionality.
 */

#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <cs50.h>
#include "dictionary.h"

#define MAX_ARRAY_LENGTH 27
#define DICT_LIMIT 45
#define APOSTROPHE_INDEX 26

// necessary for trie
typedef struct _node
{
    bool isWord;
    struct _node* children[MAX_ARRAY_LENGTH];
} node;

node root = {false, {NULL}};

bool loaded = false;
unsigned int count = 0;

bool unload_rec(node *ptr);

/**
 * Returns true if word is in dictionary else false.
 */
bool check(const char *word)
{
    // eprintf("searching word %s\n", word);
    if (word == NULL)
    {
        return false;
    }
    
    node *ptr = &root;
    for (int i = 0, n = strlen(word); i < n; i++)
    {
        char c = word[i];
        if (!isalpha(c) && c != '\'')
        {
            return false;
        }
        int index = (isalpha(c)) ? tolower(c) - 'a' : APOSTROPHE_INDEX;
        if (ptr -> children[index] == NULL)
        {
            return false;
        }
        ptr = ptr -> children[index];
    }
    return ptr -> isWord;
}

/**
 * Loads dictionary into memory. Returns true if successful else false.
 */
bool load(const char *dictionary)
{
    
    // eprintf("loading dict\n");
    FILE *in = fopen(dictionary,"r");
    
    // if unable to read, return false
    if (in == NULL)
    {
        return false;
    }
    
    
    while(!feof(in))
    {
        char* word = malloc(sizeof(char) * (DICT_LIMIT + 1));
        fscanf(in, "%s\n", word);
        
        // eprintf("adding %s\n", word);
        node *curr = &root;
        for (int i = 0, n = strlen(word); i < n; i++)
        {
            // if the value is an apostrophe, it is at index 26
            // otherwise the index is the word[i] - 'a'
            int index = (isalpha(word[i])) ? word[i] - 'a' : APOSTROPHE_INDEX;
            
            // if the pointer corresponding to the index is null, create a new node
            if (curr -> children[index] == NULL)
            {
                node *next = malloc(sizeof(node));   
                *next = (node) {false, {NULL}};
                curr -> children[index] = next;
            }
            
            // eprintf("added %c in %s\n", word[i], word);
            // move the current pointer
            curr = curr -> children[index];
            // eprintf("moved ptr\n");
           
        }
        // eprintf("added %s\n", word);
        
        // since the word is added, curr should point to an isWord node
        curr -> isWord = true;
        count++;
        free(word);
    }
    
    loaded = true;
    fclose(in);
    // eprintf("dict loaded\n");
    return true;
}

/**
 * Returns number of words in dictionary if loaded else 0 if not yet loaded.
 */
unsigned int size(void)
{
    if (!loaded)
        return 0;
    return count;
}

/**
 * Unloads dictionary from memory. Returns true if successful else false.
 */
bool unload(void)
{
    for (int i = 0; i < MAX_ARRAY_LENGTH; i++)
    {
        unload_rec(root.children[i]);
    }
    return true;
}

/**
 * Unloads dictionary recursively. Unloads the value of the children, then unloads itself
 */
bool unload_rec(node *ptr)
{
    // eprintf("freeing node");
    if (ptr == NULL)
    {
        return true;
    }
    // unload children
    for (int i = 0; i < MAX_ARRAY_LENGTH; i++)
    {
        unload_rec(ptr -> children[i]);
    }
    // unload self
    free(ptr);
    return true;
}


#include<stdio.h>

//Behaviours

int main()
{
    int x=10;
    int *ptr;
    ptr=&x;
    
    char ch='A';
    char *chr;
    chr=&ch;
    
    printf("\n x : %d",x);
    printf("\n &x : %d",ptr);
    printf("\n ptr : %d",ptr);
    printf("\n &ptr : %d",&ptr);
    printf("\n *ptr : %d",*ptr);
printf("\n\n");
    
    printf("\n ch : %c",ch);
    printf("\n &ch : %d",chr);
    printf("\n chr : %d",chr);
    printf("\n &chr : %d",&chr);
    printf("\n *chr : %d",*chr);
printf("\n\n");
    
    chr=&x; //upcasting
    printf("\n x : %d",x);
    printf("\n &x : %d",chr);
    printf("\n chr : %d",chr);
    printf("\n &chr : %d",&chr);
    printf("\n *chr : %d",*chr);

printf("\n\n");
    
    ptr=&ch;//downcasting
    printf("\n ch : %c",ch);
    printf("\n &ch : %d",ptr);
    printf("\n ptr : %d",ptr);
    printf("\n &ptr : %d",&ptr);
    printf("\n *ptr : %d",*ptr);

    


    
printf("\n\n");
    return 0;
}

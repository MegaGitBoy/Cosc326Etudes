/**
 * Etude-13 Bug Squashing
 * @author Leo Venn
 */

#include <stdio.h>

#include <stdlib.h>

#include <string.h>

#include <ctype.h>

/*max size for buffer for storing read line from input:*/
#define MAX_MEM 4096

/*Stores clients data. */
typedef struct ClientRecord {
  char *firstName;
  char *lastName;
  char *phone;
  char *emailAddress;
}
ClientRecord;

/*Allocate memory. */
void * emalloc(size_t s) {
  void * result = malloc(s);
  if (NULL == result) {
    printf("Memory allocation failed\n");
    exit(EXIT_FAILURE);
  }
  return result;
}

/*Re-allocate memory. */
void * erealloc(void * a, size_t s) {
  void * result = realloc(a, s);
  if (NULL == result) {
    fprintf(stderr, "Memory allocation failed\n");
    exit(EXIT_FAILURE);
  }
  return result;
}

/*Sorts client records based off of user input*/
void sort(ClientRecord * record, size_t n, char *option) {
  ClientRecord temp;
  size_t i = 0;
  size_t j = 0;

  for (i = 0; i < n; i++) {
    for (j = 0; j < n - 1 - j; j++) {
      if (strcmp(option , "f") == 0) {
        if (strcmp(record[j].firstName, record[j + 1].firstName) > 0) {
          temp = record[j];
          record[j] = record[j + 1];
          record[j + 1] = temp;
        }
      } else if (strcmp(option , "l") == 0) {
        if (strcmp(record[j].lastName, record[j + 1].lastName) > 0) {
          temp = record[j];
          record[j] = record[j + 1];
          record[j + 1] = temp;
        }
      } else if (strcmp(option , "p") == 0) {
        if (strcmp(record[j].phone, record[j + 1].phone) > 0) {
          temp = record[j];
          record[j] = record[j + 1];
          record[j + 1] = temp;
        }
      } else if (strcmp(option , "e") == 0) {
        if (strcmp(record[j].emailAddress, record[j + 1].emailAddress) > 0) {
          temp = record[j];
          record[j] = record[j + 1];
          record[j + 1] = temp;
        }
      }
    }
  }

}

/*Finds records based off of client input*/
void find(ClientRecord * record, size_t record_size, char * search_parameter, char * option) {

  int matchCount = 0;
  size_t i = 0;
  if (strcmp(option , "f") == 0) {

    for (i = 0; i < record_size; i++) {
      if (strcmp(search_parameter, record[i].firstName) == 0) {

        printf("[%s; %s; %s; %s] \n", record[i].firstName, record[i].lastName, record[i].phone, record[i].emailAddress);
        matchCount++;
      }
    }

    if (matchCount != 0) {
      printf("%d entries found for first_name: %s \n", matchCount, search_parameter);
    } else {
      printf("No Entries found for first name: %s \n", search_parameter);
    }
  }

  if (strcmp(option , "l") == 0) {
    for (i = 0; i < record_size; i++) {
      if (strcmp(search_parameter, record[i].lastName) == 0) {

        printf("[%s; %s; %s; %s] \n", record[i].firstName, record[i].lastName, record[i].phone, record[i].emailAddress);
        matchCount++;
      }
    }
    if (matchCount != 0) {
      printf("%d entries found for last name: %s \n", matchCount, search_parameter);
    } else {
      printf("No Entries found for last name: %s \n", search_parameter);
    }
  }

  if (strcmp(option , "p") == 0) {
    for (i = 0; i < record_size; i++) {
      if (strcmp(search_parameter, record[i].phone) == 0) {

        printf("[%s; %s; %s; %s] \n", record[i].firstName, record[i].lastName, record[i].phone, record[i].emailAddress);
        matchCount++;
      }
    }
    if (matchCount != 0) {
      printf("%d entries found for phone number: %s \n", matchCount, search_parameter);
    } else {
      printf("No Entries found for phone number: %s \n", search_parameter);
    }
  }

  if (strcmp(option , "e") == 0) {
    for (i = 0; i < record_size; i++) {
      if (strcmp(search_parameter, record[i].emailAddress) == 0) {

        printf("[%s; %s; %s; %s] \n", record[i].firstName, record[i].lastName, record[i].phone, record[i].emailAddress);
        matchCount++;
      }
    }
    if (matchCount != 0) {
      printf("%d entries found for email address: %s \n", matchCount, search_parameter);
    } else {
      printf("No Entries found for email address: %s \n", search_parameter);
    }
  }

}



/**
 * The main application. Reads in database of users
 * and takes user input to search for records
 *
 * @param argc Number of arguements.
 * @param argv Array of arguement values.
 * @return Returns 0 if no errors.
 */

int main(int argc, char **argv) {

  /*Count of how many records */
  int recordCount = 0;
  /*Initial starting capacity of array of structs holding client record*/
  int recordCapacity = 888;
  /*Store user entered parameters*/
  /*stores argument of what user is searching for e.g. f for first name*/
  char searchParameter[MAX_MEM];
  /*client record used to search*/
  char searchClientRecord[MAX_MEM];
  /*Struct that stores client data*/
  ClientRecord client;
  /*Store all client data structs as a record*/
  ClientRecord * clientRecArray = emalloc(recordCapacity * sizeof( * clientRecArray));
  /*how many commands are give*/
  int commandAmount;
  /*input from user as a line*/
  char inputLine[MAX_MEM];
  
  
  
  int i;
  
  /*Open database*/
  FILE * file_database = fopen(argv[1], "r");


  
  /*Check if arguenments given is 2*/
  if (argc != 2) {
    printf("Invalid arguements. Make sure you execute the program in this format: %s <database_file>\n", argv[0]);
    exit(0);
  }

  /*File not found*/
  if (!file_database) {
    perror("Error: ");
    exit(EXIT_FAILURE);
  }


 
  
    
   
    /*read in database data*/  
  do{
    client.firstName = emalloc( 80 * sizeof( * client.firstName));

    client.lastName = emalloc(80 * sizeof( * client.lastName));

    client.phone = emalloc(20 * sizeof( * client.phone));

    client.emailAddress = emalloc(50 * sizeof( * client.emailAddress));    
    
    clientRecArray[recordCount++] = client;  
    if (recordCount == recordCapacity) {
      recordCapacity += recordCapacity;
      clientRecArray = erealloc(clientRecArray, recordCapacity * sizeof clientRecArray[0]);

    }
    

      
  } while (4==fscanf(file_database, "%80s %80s %20s %50s", client.firstName, client.lastName, client.phone, client.emailAddress  ));
    


  fclose(file_database);

  /*Main loop to get user input*/
  while (1) {
    printf("\n");
    printf("\n");
    printf("Command Format: f <query>, l <query>, p <query>, e <query>, q (quit application) \n");

    /*Get input from user and check if valid or invalid using fgets to get whole line then processing tokens*/
    fgets(inputLine, MAX_MEM, stdin);
    char * token = strtok(inputLine, " ");
    strcpy(searchParameter, token);
    token = strtok(NULL, " ");
    searchParameter[strcspn(searchParameter, "\n")] = 0;
    commandAmount = 1;
    while( token != NULL ) {
      strcpy(searchClientRecord, token);
      searchClientRecord[strcspn(searchClientRecord, "\n")] = 0;
      token = strtok(NULL, " ");
      commandAmount ++;
   }
    

    /*check if amount of commands given is a valid search or quit command then execute accordingly*/
    if (commandAmount == 2) {
        if(strcmp(searchParameter, "f")*strcmp(searchParameter, "l")*strcmp(searchParameter, "e")*strcmp(searchParameter, "p") == 0){
            sort( & clientRecArray[0], recordCount, searchParameter);
            find( & clientRecArray[0], recordCount, searchClientRecord, searchParameter);
            
            
            
        }else{
            
            printf("Invalid arguements: Please see command format below (f for first name search, l for last, p for phone number, e for email)");
        }
        
    }else if (commandAmount == 1 && strcmp(searchParameter, "q") == 0){
        break;
    }else if (commandAmount == -1){
        break;
    }else{
        printf("Invalid arguements: Please see command format below (f for first name search, l for last, p for phone number, e for email)");
    }
}
     
/*free memory*/
  for (i = 0; i < recordCount; i++) {
    free(clientRecArray[i].firstName);
    free(clientRecArray[i].lastName);
    free(clientRecArray[i].phone);
    free(clientRecArray[i].emailAddress);
  }
  free(clientRecArray);

  printf("Thank you for using my programn.\n");
  return EXIT_SUCCESS;

}
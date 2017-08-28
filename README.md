# MonteCarloLeadTime
Java system to run monte carlo simulations based on LT.


## How to run it
### 1. Manually inputting data
Every editable field in in "main" :P

Possible Editions
• globalWIPLimit -> Amount of cards allowed in the system.

• columnNames -> Array that stores the Kanban board column names

• namesOfColumnsAffectedByGlobalWIPLimit -> Array that sotres the name of the columns that should be affected by Global WIP Limit

• wipLimits -> array with the columns WIP limits (the backlog WIP Limit is the number of cards you want in your simulation, and the last column's WIP Limit has to be the same as the backlog's)

• leadTimeCandidatesPerColumn -> Columns Lead Time history to be used in each simulation. For the first and last column, it should be 0.

It is important to notice that the order of the values in the array should be the same.


### 2. Using Google Sheets
Create a new google sheets document.
Follow the following structure on your sheet:
line 1: Column Names
line 2: Put "yes" or "no" if they should be affected by global wip limit
line 3: Put the WIP Limit for each column (the backlog WIP Limit is the number of cards you want in your simulation, and the last column's WIP Limit has to be the same as the backlog's)
line 4: Lead Time data for each column
line 5: Global Wip Limit Value

![Alt text](SpreadsheetExample.png?raw=true "")

On the GoogleSheetsConnection.java file, edit the spreadsheetID and readingRange according to your specifications


## Results
### 1. Terminal printed
If you run it on your terminal, or on an IDE, you will receive all the simulations LTs, one per line.

### 2. Spreadsheet results
On the GoogleSheetsConnection.java file, edit the spreadsheetID and writingRange according to your specifications

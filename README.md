# MonteCarloLeadTime
Java system to run monte carlo simulations based on LT.


## How to run it
Every editable field in in "main" :P

Possible Editions
• globalWIPLimit -> Amount of cards allowed in the system.

• columnNames -> Array that stores the Kanban board column names

• namesOfColumnsAffectedByGlobalWIPLimit -> Array that sotres the name of the columns that should be affected by Global WIP Limit

• wipLimits -> array with the columns WIP limits (the backlog WIP Limit is the number of cards you want in your simulation, and the last column's WIP Limit has to be the same as the backlog's)

• leadTimeCandidatesPerColumn -> Columns Lead Time history to be used in each simulation. For the first and last column, it should be 0.

It is important to notice that the order of the values in the array should be the same.

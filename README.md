# Check-database-schema
check database schema following a set of rules

## Supported databases
* Oracle
* HSQLDB

## Rules

### Empty line
an empty line or a line containing only space in not taken in account

### Commented rule
rule started by #

### Check if the database exists
database exists

### Check if a table exists
table &lt;tableName&gt; exists

### Check if a table does not exist
table &lt;tableName&gt; does not exist

## File example

```
# This is a comment following by an empty line

database exists
table FOO exists
table FOO2 exists
table FOO3 does not exist
# End file
```



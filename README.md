# Check-database-schema
Check database schema following a set of rules

## Supported databases
* Oracle
* HSQLDB

## Rules

### Empty lines
an empty line or a line containing only space in not taken in account

### Commented rules
rule started by #

### Check if the database exists
database exists

### Check if a table exists
#### One table
table &lt;tableName&gt; exists

#### Several tables
tables &lt;tableName1&gt;,&lt;tableName2&gt;... exist


### Check if a table does not exist
table &lt;tableName&gt; does not exist

### Trim rule
the rule are trimmed 
" table foo exists  "
becomes
"table foo exists"

## File example

```
# This is a comment following by an empty line

database exists
table FOO exists
table FOO2 exists
table FOO3 does not exist
# End file
```



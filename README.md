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
* database exists
* Database exists

### Check if a table exists
#### One table
* table &lt;tableName&gt; exists
* Table &lt;tableName&gt; exists


#### Several tables
* tables &lt;tableName1&gt;,&lt;tableName2&gt;... exist
* Tables &lt;tableName1&gt;,&lt;tableName2&gt;... exist


### Check if a table does not exist
#### One table
* table &lt;tableName&gt; does not exist
* Table &lt;tableName&gt; does not exist

#### Several tables
* tables &lt;tableName1&gt;,&lt;tableName2&gt;... do not exist
* Tables &lt;tableName1&gt;,&lt;tableName2&gt;... do not exist

### Check field
* table &lt;tableName&gt; with fields &lt;field1&gt;,&lt;field2&gt;... exists
* Table &lt;tableName&gt; with fields &lt;field1&gt;,&lt;field2&gt;... exists

### Trim rule
the rule are trimmed 
" table foo exists  "
becomes
"table foo exists"

## File example

```
# This is a comment following by an empty line

database exists
Table FOO exists
Table FOO2 exists
#
#better in one line
Tables FOO,FOO2 exit
#
Table FOO3 does not exist
Table FOO4 does not exist
#better in one line
Tables FOO,FOO2 does not exit
# End file
```



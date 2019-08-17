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

## File example

<code>
# This is a comment following by an empty line<br>
<br>
database exists<br>
table FOO exists<br>
table FOO2 exists<br>
# End file<br>
</code>



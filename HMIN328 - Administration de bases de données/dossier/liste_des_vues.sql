Instance
v$instance: displays the state of the current instance. (instance_number, instance_name, host_name, version, status, startup_time, ….)

SGA
v$sga: displays summary information about the system global area (name, value)
v$sgastat: displays detailed information on the system global area (pool, name, bytes)

SharedPool [
	Library cache
		v$sqltext: displays the text of SQL statements belonging to shared SQL cursors in the SGA (address, hash_value, sql_id, command_type, piece, sql_text)
		v$sqlarea: displays statistics on shared SQL areas and contains one row per SQL string. It provides statistics on SQL statements that are in memory, parsed, and ready for execution. (sql_text, sql_id, sharable_mem, persistent_mem, ….)
		v$sql: lists statistics on shared SQL areas without the GROUP BY clause and contains one row for each child of the original SQL text entered. Statistics displayed in V$SQL are normally updated at the end of query execution. However, for long running queries, they are updated every 5 seconds. This makes it easy to see the impact of long running SQL statements while they are still in progress. (sql_text, sql_id, sharable_mem, persistent_mem, ….)
		v$librarycache: contains statistics about library cache performance and activity (namespace, gets, gethits, …)
	Dictionnary cache
		v$rowcache: displays statistics for data dictionary activity. Each row contains statistics for one data dictionary cache (cache#, type, parameter, count, usage, ….)
]
DataBaseBufferCache
	v$bh: displays the status and number of pings for every buffer in the SGA. This is a Real Application Clusters view (file#, block#, class#, status, ….)

RedoLogBuffer
	v$log: displays log file information from the control file (group#, bytes, blocksize, members,status, ….)
	v$log_history: displays log history information from the control file (recid, stamp, first_Time, ...)

Process
v$process: displays information about the currently active processes (addr, PID, SPID, Pname, username, ….)

BgProcess
v$bgprocess: displays information about the background processes (Paddr, Pserial#, name, description, error)


Database
v$database: displays information about the database from the control file (DBID, name, created, log_mode, ...)

File [
	ControlFile
		v$controlfile: This view lists the names of the control files (status, name)
	Logfile
		v$logfile: This view contains information about redo log files (groupe#, status, type, member, is_recovery_dest_file)
	DataFile
		v$datafile: This view contains datafile information from the control file (file#, creation_change, creation_time, status, ….)
		dba_data_files: describes database files (file_name, file_id, tablespace_name, bytes, blocks, status, ….)
		DBA_FREE_SPACE: describes the free extents in all tablespaces in the database (tablespace_name, file_id, block_id, bytes, blocks, relative_fno)
]

Tablespace
v$tablespace: This view displays tablespace information from the control file (TS#, name, bigfile, included_in_database_backup, flashback_on, encrypt_in_backup) 
dba_tablespaces: describes all tablespaces in the database (tablespace_name, block_size, initial_extent, status, ….)

Segment
dba_segments: describes the storage allocated for all segments in the database (owner, segment_name, partition_name, segment_type, tablespace_name, ….)

Extent
dba_extents: describes the extents comprising the segments in all tablespaces in the database (owner, segment_name, partition_name, segment_type, tablespace_name, extent_id, block_id, ….)

Block
v$parameter: displays information about the initialization parameters that are currently in effect for the session. A new session inherits parameter values from the instance-wide values displayed by the V$SYSTEM_PARAMETER view (num, name, type, value, display_value, ….)

Object [
dba_objects: describes all objects in the database (owner, object_name, subobject_name, object_id, object_type, ….)

	Table
		dba_tables: describes all relational tables in the database ( owner, table_name, tablespace_name, cluster_name, status, ….)
	Index
		dba_indexes: describes all indexes in the database (owner, index_name, index_type, table_owner, table_name, ….)
]

Lock
v$lock: This view lists the locks currently held by the Oracle Database and outstanding requests for a lock or latch (addr, kaddr, SID, type, ….)

Transaction
v$transaction: lists the active transactions in the system (addr, xidusn, xidslot, xidsqn, ubafil, …)

Session
v$session: This view lists session information for each current session (saddr, SID, serial#, audsid, ….)

User
dba_users: describes all users of the database (username, user_id, password, account_status, ….)

Role
dba_roles: lists all roles that exist in the database (rôle, password_required)
dba_role_privs: describes the roles granted to all users and roles in the database (grantee, granted_role, admin_option, default_role)

Privileges [
	Privilege System
		dba_tab_privs: describes all object grants in the database (Grantee, owner, table_name, grantor, ….)
	Privilege Utilisateur
		dba_sys_privs: describes system privileges granted to users and roles (grantee, privilege, admin_option)
]

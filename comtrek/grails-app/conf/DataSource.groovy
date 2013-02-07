/*******************************************************************************
 * Copyright (c) 2013 ComTrek.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     ComTrek - initial API and implementation
 ******************************************************************************/
dataSource {
    pooled = true
    username = "comtrek"
    password = "ivvqcomtrek"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
         dataSource {
            dbCreate = "update"
            //url = "java:comp/env/jdbc/comtrek_db;MVCC=TRUE;LOCK_TIMEOUT=10000"
            //url = "jdbc:h2:comtrek_db;MVCC=TRUE;LOCK_TIMEOUT=10000"
			driverClassName = "com.cloudbees.jdbc.Driver"
			url = "[[jdbc:cloudbees://comtrek_db]]"

            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
    test {
         dataSource {
            dbCreate = "update"
			driverClassName = "com.mysql.jdbc.Driver"
			dialect = org.hibernate.dialect.MySQL5InnoDBDialect
            url = "jdbc:mysql://localhost/comtrek_db"
			
<<<<<<< HEAD
			//url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
			username = "comtrek"
			password = "ivvqcomtrek"
=======
//			url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
			//username = "comtrek"
			//password = "ivvqcomtrek"
>>>>>>> defdc40916a370998fda12f25838e8b204625723
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "java:comp/env/jdbc/comtrek_db;MVCC=TRUE;LOCK_TIMEOUT=10000"
            url = "jdbc:h2:comtrek_db;MVCC=TRUE;LOCK_TIMEOUT=10000"
//			driverClassName = "com.cloudbees.jdbc.Driver"
//			url = "[[jdbc:cloudbees://comtrek_db]]"

            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}

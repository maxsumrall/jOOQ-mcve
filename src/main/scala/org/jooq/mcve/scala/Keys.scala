/*
 * This file is generated by jOOQ.
 */
package org.jooq.mcve.scala


import org.jooq.TableField
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.mcve.scala.tables.Test
import org.jooq.mcve.scala.tables.records.TestRecord


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * mcve.
 */
object Keys {

  // -------------------------------------------------------------------------
  // UNIQUE and PRIMARY KEY definitions
  // -------------------------------------------------------------------------

  val TEST_PKEY: UniqueKey[TestRecord] = Internal.createUniqueKey(Test.TEST, DSL.name("test_pkey"), Array(Test.TEST.ID).asInstanceOf[Array[TableField[TestRecord, _] ] ], true)
}

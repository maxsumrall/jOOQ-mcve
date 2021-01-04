package org.jooq.mcve.test.java;

import static org.jooq.mcve.java.Tables.TEST;
import static org.junit.Assert.assertEquals;

import org.jooq.impl.DSL;
import org.jooq.mcve.models.Status;
import org.junit.Test;

public class JavaTest extends AbstractTest {

  @Test
  public void mcveTest() {
    ctx.insertInto(TEST)
        .columns(TEST.STATUS)
        .values(Status.GOOD)
        .execute();

    Status status = ctx.select(TEST.STATUS).from(TEST).where(TEST.STATUS.eq(DSL.any(Status.GOOD)))
        .fetchOneInto(Status.class);
    
    assertEquals(Status.GOOD, status);
  }
}

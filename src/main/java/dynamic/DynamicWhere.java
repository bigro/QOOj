package dynamic;

import static org.jooq.impl.DSL.trueCondition;
import static qooj.jooq.generate.tables.Test1.TEST1;

import java.math.BigDecimal;
import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;

import qooj.jooq.generate.tables.records.Test1Record;

public class DynamicWhere {
	private DSLContext dslContext;

	public DynamicWhere(DSLContext dslContext) {
		this.dslContext = dslContext;
	}

	public List<Test1Record> dynamicFind(BigDecimal code, String name) throws Exception {
		SelectJoinStep<Record> step = dslContext
			.select().from(TEST1);
		Condition condition = trueCondition();
		if (code != null) {
			condition = condition.and(TEST1.CODE.equal(code));
		}
		if (name != null && !name.isEmpty()) {
			condition = condition.and(TEST1.NAME.equal(name));
		}
		step.where(condition);

		return step.fetch().into(Test1Record.class);
	}
}

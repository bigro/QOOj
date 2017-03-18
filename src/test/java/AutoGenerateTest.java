import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.jooq.Record;
import org.junit.Test;

import qooj.jooq.generate.tables.Test1;
import qooj.jooq.generate.tables.records.Test1Record;
import support.TestContext;

public class AutoGenerateTest {

	@Test
	public void test() throws Exception {
		try (TestContext testContext = new TestContext()) {
			List<Test1Record> withgenarate = testContext.dslContext()
				.select().from(Test1.TEST1)
				.where("code = 1")
				.fetch().into(Test1Record.class);
			System.out.println(withgenarate);
			assertThat(withgenarate).hasSize(1).extracting("name").contains("AAA");

			LinkedList<Test1Record> linkedList = new LinkedList<>();
			List<Record> withoutgenarate = testContext.dslContext()
				.select().from("qooj.test1")
				.where("code = 1")
				.fetch();
			for (Record record : withoutgenarate) {
				Test1Record test1Record = new Test1Record();

				// これを項目数分書く
				test1Record.setName(record.getValue("NAME", String.class));
				linkedList.add(test1Record);
			}
			assertThat(linkedList).hasSize(1).extracting("name").contains("AAA");
		}
	}

}

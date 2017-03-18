import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.LinkedList;
import java.util.List;

import org.jooq.Record;
import org.junit.Test;

import qooj.jooq.nest.Address;
import qooj.jooq.nest.User;
import support.TestContext;

public class NestTest {

	@Test
	public void test() throws Exception {
		try (TestContext testContext = new TestContext()) {
			List<Record> recordList = testContext.dslContext()
				.select().from("QOOJ.NEST1")
				.where("CODE = 1")
				.fetch();

			LinkedList<User> linkedList = new LinkedList<>();
			for (Record record : recordList) {
				linkedList.add(
					new User(
						record.getValue("CODE", int.class),
						record.getValue("NAMEID", int.class),
						record.getValue("ADDRESS", Address.converter())));
			}

			assertThat(linkedList)
				.extracting("code", "nameId", "address")
				.containsExactly(
					tuple(1, 101, new Address("大阪")));
		}
	}
}

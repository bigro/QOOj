import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Test;

import qooj.jooq.nest.Address;
import qooj.jooq.nest.User;

public class NestTest {

	@Test
	public void test() throws Exception {
		String userName = "sa";
		String password = "";
		String url = "jdbc:h2:tcp://localhost:9092/qooj;MODE=MySQL;";

		try (Connection conn = DriverManager.getConnection(url, userName, password)) {
			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

			List<Record> recordList = create
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

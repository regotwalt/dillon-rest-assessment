package com.hoopladigital.integration;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.junit.Test;

import static org.junit.Assert.*;

public class PetEndpointIntegrationTest extends IntegrationHelperTest {

	// TODO: Improve tests once a test server is used (see TODO in IntegrationHelperTest)

	private String createPetJson(final Long personId, final String name) {
		return createPetJson(personId, name, null);
	}

	private String createPetJson(final Long personId, final String name, final Long id) {
		final StringBuilder builder = new StringBuilder("{");
		if (id != null) {
			builder.append("\"id\":").append(id).append(",");
		}
		return builder
			.append("\"personId\":").append(personId).append(",")
			.append("\"name\":\"").append(name).append("\"")
			.append("}")
			.toString();
	}

	@Test
	public void should_get_pet_list_endpoint() throws IOException {
		final HttpURLConnection connection = makeRequest("pets", "GET");
		final String returnedJson = readResponseJson(connection);

		assertEquals(200, connection.getResponseCode());
		// TODO: Test full response once rollbacks are working (i.e. tests not dependent on external server)
		assertTrue(returnedJson.contains(createPetJson(1L, "Lady Rover", 14L)));
		assertTrue(returnedJson.contains(createPetJson(7L, "Lady Nashville", 26L)));
	}

	@Test
	public void should_get_pet_by_id_endpoint() throws IOException {
		final Long id = 31L;
		final HttpURLConnection connection = makeRequest("pets/" + id, "GET");
		final String returnedJson = readResponseJson(connection);
		final String expectedReturn = createPetJson(10L, "The General", id);

		assertEquals(200, connection.getResponseCode());
		assertEquals(expectedReturn, returnedJson);
	}

	@Test
	public void should_get_pet_by_id_invalid_id_endpoint() throws IOException {
		final Long id = 1_000L;
		final HttpURLConnection connection = makeRequest("pets/" + id, "GET");
		final String returnedJson = readResponseJson(connection);

		assertEquals(404, connection.getResponseCode());
		assertNull(returnedJson);
	}

	@Test
	public void should_create_pet_endpoint() throws IOException {
		final String json = createPetJson(10L, "Sgt Pepper");
		final HttpURLConnection connection = makeRequest("pets", "POST", json);
		final String returnedJson = readResponseJson(connection);
		// TODO: Uncomment the following once rollbacks are working (also delete expectedJsonStart, expectedJsonEnd)
		// final String expectedJson = createPetJson(10L, "Sgt Pepper", 34L);
		final String expectedJsonStart = "{\"id\":";
		final String expectedJsonEnd = ",\"personId\":10,\"name\":\"Sgt Pepper\"}";

		assertEquals(200, connection.getResponseCode());
		// TODO: Uncomment the following once rollbacks are working (also delete the last 2 asserts)
		// assertEquals(expectedJson, returnedJson);
		assertTrue(returnedJson.startsWith(expectedJsonStart));
		assertTrue(returnedJson.endsWith(expectedJsonEnd));
	}

	@Test
	public void should_create_pet_empty_pet_endpoint() throws IOException {
		final HttpURLConnection connection = makeRequest("pets", "POST", "{}");
		final String returnedJson = readResponseJson(connection);

		assertEquals(400, connection.getResponseCode());
		assertNull(returnedJson);
	}

	@Test
	public void should_create_pet_invalid_person_id_endpoint() throws IOException {
		final String json = createPetJson(1_000L, "Sgt Pepper");
		final HttpURLConnection connection = makeRequest("pets", "POST", json);
		final String returnedJson = readResponseJson(connection);

		assertEquals(400, connection.getResponseCode());
		assertNull(returnedJson);
	}

	@Test
	public void should_create_pet_large_name_endpoint() throws IOException {
		final String json = createPetJson(10L, over50name);
		final HttpURLConnection connection = makeRequest("pets", "POST", json);
		final String returnedJson = readResponseJson(connection);

		assertEquals(400, connection.getResponseCode());
		assertNull(returnedJson);
	}

	@Test
	public void should_update_pet_endpoint() throws IOException {
		final Long id = 1L;
		final Long personId = 10L;
		final String name = "Barbara";
		final String json = createPetJson(personId, name);

		final HttpURLConnection connection = makeRequest("pets/" + id, "PUT", json);
		final String returnedJson = readResponseJson(connection);
		final String expectedJson = createPetJson(personId, name, id);

		assertEquals(200, connection.getResponseCode());
		assertEquals(expectedJson, returnedJson);
	}

	@Test
	public void should_update_pet_empty_pet_endpoint() throws IOException {
		final HttpURLConnection connection = makeRequest("pets/1", "PUT", "{}");
		final String returnedJson = readResponseJson(connection);

		assertEquals(400, connection.getResponseCode());
		assertNull(returnedJson);
	}

	@Test
	public void should_update_pet_invalid_id_endpoint() throws IOException {
		final Long id = 1_000L;
		final Long personId = 10L;
		final String name = "Barbara";
		final String json = createPetJson(personId, name);

		final HttpURLConnection connection = makeRequest("pets/" + id, "PUT", json);
		final String returnedJson = readResponseJson(connection);

		assertEquals(404, connection.getResponseCode());
		assertNull(returnedJson);
	}

	@Test
	public void should_update_pet_large_name_endpoint() throws IOException {
		final Long id = 1L;
		final String json = createPetJson(10L, over50name);

		final HttpURLConnection connection = makeRequest("pets/" + id, "PUT", json);
		final String returnedJson = readResponseJson(connection);

		assertEquals(400, connection.getResponseCode());
		assertNull(returnedJson);
	}

	// TODO: Uncomment once rollback is enabled (only works once as it deletes the entity)
//	@Test
//	public void should_delete_pet_endpoint() throws IOException {
//		final Long id = 25L;
//		final HttpURLConnection connection = makeRequest("pets/" + id, "DELETE");
//
//		assertEquals(204, connection.getResponseCode());
//	}

	@Test
	public void should_delete_pet_invalid_id_endpoint() throws IOException {
		final Long id = 1_000L;
		final HttpURLConnection connection = makeRequest("pets/" + id, "DELETE");

		assertEquals(404, connection.getResponseCode());
	}

}

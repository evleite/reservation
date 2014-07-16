package com.prodyna.pac.aaa.it;

import java.net.URL;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.pac.aaa.aircraft.AircraftType;
import com.prodyna.pac.aaa.aircraft.service.rest.AircraftTypeRESTService;
import com.prodyna.pac.aaa.aircraft.service.rest.AircraftTypeRESTServiceImpl;

/**
 * Tests for {@link AircraftTypeRESTServiceImpl}.
 * 
 * @author Sergej Herdt, PRODYNA AG
 * 
 */
@RunWith(Arquillian.class)
public class AircraftTypeRESTTest {

	@ArquillianResource
	private URL url;

	protected Client createClient() {
		final Client client = ClientBuilder.newClient();
		client.register(JsonProcessingFeature.class); // needed for "normal"
		client.register(JacksonFeature.class); // needed for dynamic pr
		return client;
	}

	protected WebTarget createWebTarget() {
		final String fullPath = this.url.toString() + "rest-api";
		final WebTarget target = this.createClient().target(fullPath);
		return target;
	}

	protected <C> C createService(final Class<C> ifaceType) {
		return WebResourceFactory.newResource(ifaceType, this.createWebTarget());
	}

	@Deployment
	public static Archive<?> createArchive() {
		final WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test-aircraft-type-rest.war");
		webArchive.addPackages(true, "com.prodyna.pac.aaa");
		webArchive.addAsResource("persistence.xml", "META-INF/persistence.xml");
		webArchive.addAsResource("beans.xml", "META-INF/beans.xml");
		webArchive.as(ExplodedImporter.class).importDirectory("../aaa-web/src/main/webapp");
		webArchive.addClass(com.prodyna.pac.aaa.rest.RESTActivator.class);
		return webArchive;
	}

	@Test
	@RunAsClient
	@InSequence(1)
	public void createAndDeleteAircraftTypeA380() {
		final AircraftTypeRESTService aircraftTypeRESTService = this.createService(AircraftTypeRESTService.class);

		List<AircraftType> allAircraftTypes = aircraftTypeRESTService.listAircraftTypes();
		Assert.assertEquals(0, allAircraftTypes.size());

		final AircraftType aircraftType = aircraftTypeRESTService.createAircraftType(new AircraftType("A-380"));
		Assert.assertEquals("A-380", aircraftType.getName());

		allAircraftTypes = aircraftTypeRESTService.listAircraftTypes();
		Assert.assertEquals(1, allAircraftTypes.size());

		aircraftTypeRESTService.deleteAircraftType(aircraftType.getName());
		allAircraftTypes = aircraftTypeRESTService.listAircraftTypes();
		Assert.assertEquals(0, allAircraftTypes.size());
	}

}

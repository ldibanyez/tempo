import org.intalio.tempo.workflow.wds.core.Item
import org.intalio.tempo.workflow.wds.core.Item
import org.intalio.tempo.workflow.wds.core.ItemDaoConnection
import org.intalio.tempo.workflow.wds.core.JPAItemDaoConnectionFactory
import org.intalio.tempo.workflow.wds.core.JPAItemDaoConnection

import java.net.URI

def getJPAProperties() {
	props = new Properties()
	props.put("openjpa.ConnectionDriverName","org.apache.derby.jdbc.EmbeddedDriver")
	props.put("openjpa.ConnectionURL","jdbc:derby:wds-service/target/JPADB;create=true")
	props.put("openjpa.jdbc.SynchronizeMappings","buildSchema")
	return props
}
def getSampleItem() {
	return new Item("http://www.hellonico.net", "meta", "hello".getBytes())
}

scenario "storing items using jpa", {
	given "a JPA Item connection ", {
		factory = new JPAItemDaoConnectionFactory(getJPAProperties())
		jpac = (JPAItemDaoConnection)factory.getItemDaoConnection()  	
	}
	when "an item is stored", {
		item = getSampleItem()
		jpac.storeItem(item)
	}
	then "I can retrieve it", {
		item2 = jpac.retrieveItem(item.URI)
	}
	then "the retrieved item is the same as the original", {
		ensure(item) {
			isEqualTo item2
		}
	}
	and 
	when "I delete the item", {
		jpac.deleteItem(item.URI)
	}
	then "trying to retrieve it should fail", {
		ensureThrows(Exception.class){
			jpac.retrieveItem(item.URI)
		}
	}
}
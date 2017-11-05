package Backbone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Logger;

/**
 * Created by jcabral on 24. 3. 2017.
 */
public class OpenXML<W, E> {
    Logger log = Logger.getLogger(OpenXML.class.getName());

    private W wrapper;

    public W getObject() {
        return wrapper;
    }

    @SuppressWarnings("unchecked")
	public OpenXML(String path, Class<W> classWrapper, Class<E> classElement) {
        try {
            File f = new File(path);
            JAXBContext jaxb = JAXBContext.newInstance(classWrapper);


            Unmarshaller unmarshaller = jaxb.createUnmarshaller();
            wrapper = (W) unmarshaller.unmarshal(f);


        } catch (JAXBException e) {
            log.severe(e.getMessage());

        } catch (Exception e) {
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
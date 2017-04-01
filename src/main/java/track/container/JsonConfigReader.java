package track.container;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import track.container.config.Bean;
import track.container.config.ConfigReader;
import track.container.config.InvalidConfigurationException;
import track.container.config.Property;
import track.container.config.ValueType;

/**
 * TODO: Реализовать
 */
public class JsonConfigReader implements ConfigReader {

    @Override
    public List<Bean> parseBeans(File configFile) throws InvalidConfigurationException {
        String pathToFile = configFile.getPath();
        List<Bean> beans = new ArrayList<Bean>();
        try {
            JsonParser jsonParser = new JsonFactory().createParser(new File(pathToFile));
            jsonParser.nextToken();
            jsonParser.nextToken();
            jsonParser.nextToken();
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                String id = new String();
                String className = new String();
                Map<String, Property> properties = new HashMap<String, Property>();
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    String name = jsonParser.getCurrentName();
                    if ("id".equals(name)) {
                        jsonParser.nextToken();
                        id = jsonParser.getText();
                    } else if ("className".equals(name)) {
                        jsonParser.nextToken();
                        className = jsonParser.getText();
                    } else if ("properties".equals(name)) {
                        jsonParser.nextToken();
                        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                            String key = jsonParser.getCurrentName();
                            jsonParser.nextToken();
                            String propertyName = new String();
                            String propertyValue = new String();
                            ValueType propertyType = ValueType.VAL;
                            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                                String curName = jsonParser.getCurrentName();
                                if ("name".equals(curName)) {
                                    jsonParser.nextToken();
                                    propertyName = jsonParser.getText();
                                } else if ("value".equals(curName)) {
                                    jsonParser.nextToken();
                                    propertyValue = jsonParser.getText();
                                } else if ("type".equals(curName)) {
                                    jsonParser.nextToken();
                                    if ("REF".equals(jsonParser.getText())) {
                                        propertyType = ValueType.REF;
                                    } else if ("VAL".equals(jsonParser.getText())) {
                                        propertyType = ValueType.VAL;
                                    } else {
                                        throw new InvalidConfigurationException("wrong type");
                                    }
                                }
                            }
                            Property property = new Property(propertyName, propertyValue, propertyType);
                            properties.put(key, property);
                        }
                    }
                }
                Bean bean = new Bean(id, className, properties);
                beans.add(bean);
            }
        } catch (IOException e) {
            throw new InvalidConfigurationException("parsing error");
        }
        return beans;
    }
}

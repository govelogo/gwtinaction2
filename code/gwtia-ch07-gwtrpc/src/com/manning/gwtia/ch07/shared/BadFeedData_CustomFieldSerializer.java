package com.manning.gwtia.ch07.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * A custom serializer for the BadFeedData class.  This is an example
 * of the code you would need to write for when GWT is unable to
 * automatically generate a serializer for you.
 */
public class BadFeedData_CustomFieldSerializer
{
  
  /**
   * Serialize the object graph into a stream.
   *
   * @param ssw the ssw
   * @param instance the instance
   * @throws SerializationException the serialization exception
   */
  public static void serialize(SerializationStreamWriter ssw,
      BadFeedData instance) throws SerializationException {
    ssw.writeObject(instance.getCreatedAt());
    ssw.writeString(instance.getText());
  }

  /**
   * Instantiate a new object instance, reading from the stream the
   * number of parameters required to instantiate the instance.
   *
   * @param ssr the stream reader
   * @return the BadFeedData instance
   * @throws SerializationException the serialization exception
   */
  public static BadFeedData instantiate(SerializationStreamReader ssr)
      throws SerializationException {
    return new BadFeedData((Date) ssr.readObject());
  }

  /**
   * Deserialize the stream back into the object graph.
   *
   * @param ssr the stream reader
   * @param instance the object instance to put the stream data into
   * @throws SerializationException the serialization exception
   */
  public static void deserialize(SerializationStreamReader ssr,
      BadFeedData instance) throws SerializationException {
    instance.setText(ssr.readString());
  }
}

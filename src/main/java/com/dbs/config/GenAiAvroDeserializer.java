package com.dbs.config;

import com.imtf.dbs.namescreening.common.kafka.old.GenAiResponseRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class GenAiAvroDeserializer implements Deserializer<GenAiResponseRecord> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenAiAvroDeserializer.class);

    @Override
    public GenAiResponseRecord deserialize(String topic, byte[] data) {
        try {
            GenAiResponseRecord result = null;
            if (data != null) {
                LOGGER.info("data='{}'", data);
                DatumReader<GenAiResponseRecord> datumReader =
                        new SpecificDatumReader<>(GenAiResponseRecord.getClassSchema());
                Decoder decoder = DecoderFactory.get().binaryDecoder(data, null);
                result = datumReader.read(null, decoder);
                LOGGER.info("deserialized data='{}'", result);
            }
            return result;
        } catch (Exception ex) {
            LOGGER.info("exception occured indeserialization {} " + ex.getMessage());
            throw new SerializationException(
                    "Can't deserialize data '" + Arrays.toString(data) + "' from topic '" + topic + "'", ex);
        }
    }
}

package tejas.cloud.assignment1.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import tejas.cloud.assignment1.proto.ProductsProtoc.GetProduct;
import tejas.cloud.assignment1.proto.ProductsProtoc.ProductSuccess;

@Provider
@Produces("application/protobuf")
@Consumes("application/protobuf")
public class WidgetProtocMessageBodyProvder implements MessageBodyReader, MessageBodyWriter {

	@Override
	public boolean isReadable(Class type, Type type1, Annotation[] antns, MediaType mt) {
		return GetProduct.class.isAssignableFrom(type) || ProductSuccess.class.isAssignableFrom(type);
	}

	@Override
	public Object readFrom(Class type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap mm, InputStream in)
			throws IOException, WebApplicationException {
		if (GetProduct.class.isAssignableFrom(type)) {
			return GetProduct.parseFrom(in);
		} else if (ProductSuccess.class.isAssignableFrom(type)) {
			return ProductSuccess.parseFrom(in);
		} else {
			throw new BadRequestException("Can't Deserailize");
		}
	}

	@Override
	public boolean isWriteable(Class type, Type type1, Annotation[] antns, MediaType mt) {
		return GetProduct.class.isAssignableFrom(type) || ProductSuccess.class.isAssignableFrom(type);
	}

	@Override
	public long getSize(Object t, Class type, Type type1, Annotation[] antns, MediaType mt) {
		return -1;
	}

	@Override
	public void writeTo(Object t, Class type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap mm,
			OutputStream out) throws IOException, WebApplicationException {
		if (t instanceof GetProduct) {
			GetProduct getProduct = (GetProduct) t;
			getProduct.writeTo(out);
		} else if (t instanceof ProductSuccess) {
			ProductSuccess list = (ProductSuccess) t;
			list.writeTo(out);
		}
	}
}

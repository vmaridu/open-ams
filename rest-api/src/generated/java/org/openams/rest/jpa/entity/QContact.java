package org.openams.rest.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QContact is a Querydsl query type for Contact
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContact extends EntityPathBase<Contact> {

    private static final long serialVersionUID = 1739503879L;

    public static final QContact contact = new QContact("contact");

    public final StringPath addressLine1 = createString("addressLine1");

    public final StringPath addressLine2 = createString("addressLine2");

    public final StringPath apartment = createString("apartment");

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    public final StringPath eMail = createString("eMail");

    public final StringPath homePhone = createString("homePhone");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath notes = createString("notes");

    public final StringPath phone = createString("phone");

    public final StringPath state = createString("state");

    public final StringPath street = createString("street");

    public final NumberPath<Integer> zip = createNumber("zip", Integer.class);

    public QContact(String variable) {
        super(Contact.class, forVariable(variable));
    }

    public QContact(Path<? extends Contact> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContact(PathMetadata metadata) {
        super(Contact.class, metadata);
    }

}


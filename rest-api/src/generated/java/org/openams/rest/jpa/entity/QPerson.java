package org.openams.rest.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPerson is a Querydsl query type for Person
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPerson extends EntityPathBase<Person> {

    private static final long serialVersionUID = 834817422L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPerson person = new QPerson("person");

    public final QContact contact;

    public final DateTimePath<java.util.Date> dob = createDateTime("dob", java.util.Date.class);

    public final QContact emrContact;

    public final EnumPath<org.openams.rest.jpa.entity.enums.EyeColor> eyeColor = createEnum("eyeColor", org.openams.rest.jpa.entity.enums.EyeColor.class);

    public final StringPath fName = createString("fName");

    public final EnumPath<org.openams.rest.jpa.entity.enums.Gender> gender = createEnum("gender", org.openams.rest.jpa.entity.enums.Gender.class);

    public final EnumPath<org.openams.rest.jpa.entity.enums.HairColor> hairColor = createEnum("hairColor", org.openams.rest.jpa.entity.enums.HairColor.class);

    public final NumberPath<Float> height = createNumber("height", Float.class);

    public final StringPath id = createString("id");

    public final DateTimePath<java.util.Date> joiningDtt = createDateTime("joiningDtt", java.util.Date.class);

    public final StringPath lName = createString("lName");

    public final StringPath mName = createString("mName");

    public final DateTimePath<java.util.Date> modifiedDtt = createDateTime("modifiedDtt", java.util.Date.class);

    public final StringPath pictureUri = createString("pictureUri");

    public final StringPath prefix = createString("prefix");

    public final StringPath race = createString("race");

    public final NumberPath<Integer> ssn = createNumber("ssn", Integer.class);

    public final StringPath suffix = createString("suffix");

    public final QUser user;

    public final NumberPath<Float> weight = createNumber("weight", Float.class);

    public QPerson(String variable) {
        this(Person.class, forVariable(variable), INITS);
    }

    public QPerson(Path<? extends Person> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPerson(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPerson(PathMetadata metadata, PathInits inits) {
        this(Person.class, metadata, inits);
    }

    public QPerson(Class<? extends Person> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.contact = inits.isInitialized("contact") ? new QContact(forProperty("contact")) : null;
        this.emrContact = inits.isInitialized("emrContact") ? new QContact(forProperty("emrContact")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}


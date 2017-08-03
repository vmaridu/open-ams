package org.openams.rest.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPayment is a Querydsl query type for Payment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPayment extends EntityPathBase<Payment> {

    private static final long serialVersionUID = 1296109L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPayment payment = new QPayment("payment");

    public final NumberPath<Float> amount = createNumber("amount", Float.class);

    public final StringPath comment = createString("comment");

    public final QFee fee;

    public final StringPath id = createString("id");

    public final DateTimePath<java.util.Date> modifiedDtt = createDateTime("modifiedDtt", java.util.Date.class);

    public final StringPath name = createString("name");

    public QPayment(String variable) {
        this(Payment.class, forVariable(variable), INITS);
    }

    public QPayment(Path<? extends Payment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPayment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPayment(PathMetadata metadata, PathInits inits) {
        this(Payment.class, metadata, inits);
    }

    public QPayment(Class<? extends Payment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fee = inits.isInitialized("fee") ? new QFee(forProperty("fee"), inits.get("fee")) : null;
    }

}


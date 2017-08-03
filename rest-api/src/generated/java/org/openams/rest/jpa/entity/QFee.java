package org.openams.rest.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFee is a Querydsl query type for Fee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFee extends EntityPathBase<Fee> {

    private static final long serialVersionUID = -195187731L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFee fee = new QFee("fee");

    public final StringPath academicTerm = createString("academicTerm");

    public final NumberPath<Float> amount = createNumber("amount", Float.class);

    public final StringPath comment = createString("comment");

    public final StringPath id = createString("id");

    public final DateTimePath<java.util.Date> modifiedDtt = createDateTime("modifiedDtt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final ListPath<Payment, QPayment> payments = this.<Payment, QPayment>createList("payments", Payment.class, QPayment.class, PathInits.DIRECT2);

    public final QStudent student;

    public QFee(String variable) {
        this(Fee.class, forVariable(variable), INITS);
    }

    public QFee(Path<? extends Fee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFee(PathMetadata metadata, PathInits inits) {
        this(Fee.class, metadata, inits);
    }

    public QFee(Class<? extends Fee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.student = inits.isInitialized("student") ? new QStudent(forProperty("student"), inits.get("student")) : null;
    }

}


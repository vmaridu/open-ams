package org.openams.rest.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStaff is a Querydsl query type for Staff
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStaff extends EntityPathBase<Staff> {

    private static final long serialVersionUID = 1415603591L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStaff staff = new QStaff("staff");

    public final QPerson _super;

    public final StringPath altId = createString("altId");

    // inherited
    public final QContact contact;

    public final ListPath<CourseSchedule, QCourseSchedule> courseSchedules = this.<CourseSchedule, QCourseSchedule>createList("courseSchedules", CourseSchedule.class, QCourseSchedule.class, PathInits.DIRECT2);

    public final StringPath desc = createString("desc");

    public final StringPath designation = createString("designation");

    //inherited
    public final DateTimePath<java.util.Date> dob;

    // inherited
    public final QContact emrContact;

    //inherited
    public final EnumPath<org.openams.rest.jpa.entity.enums.EyeColor> eyeColor;

    //inherited
    public final StringPath fName;

    //inherited
    public final EnumPath<org.openams.rest.jpa.entity.enums.Gender> gender;

    //inherited
    public final EnumPath<org.openams.rest.jpa.entity.enums.HairColor> hairColor;

    //inherited
    public final NumberPath<Float> height;

    //inherited
    public final StringPath id;

    //inherited
    public final DateTimePath<java.util.Date> joiningDtt;

    //inherited
    public final StringPath lName;

    //inherited
    public final StringPath mName;

    //inherited
    public final DateTimePath<java.util.Date> modifiedDtt;

    //inherited
    public final StringPath pictureUri;

    //inherited
    public final StringPath prefix;

    //inherited
    public final StringPath race;

    //inherited
    public final NumberPath<Integer> ssn;

    //inherited
    public final StringPath suffix;

    // inherited
    public final QUser user;

    //inherited
    public final NumberPath<Float> weight;

    public QStaff(String variable) {
        this(Staff.class, forVariable(variable), INITS);
    }

    public QStaff(Path<? extends Staff> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStaff(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStaff(PathMetadata metadata, PathInits inits) {
        this(Staff.class, metadata, inits);
    }

    public QStaff(Class<? extends Staff> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QPerson(type, metadata, inits);
        this.contact = _super.contact;
        this.dob = _super.dob;
        this.emrContact = _super.emrContact;
        this.eyeColor = _super.eyeColor;
        this.fName = _super.fName;
        this.gender = _super.gender;
        this.hairColor = _super.hairColor;
        this.height = _super.height;
        this.id = _super.id;
        this.joiningDtt = _super.joiningDtt;
        this.lName = _super.lName;
        this.mName = _super.mName;
        this.modifiedDtt = _super.modifiedDtt;
        this.pictureUri = _super.pictureUri;
        this.prefix = _super.prefix;
        this.race = _super.race;
        this.ssn = _super.ssn;
        this.suffix = _super.suffix;
        this.user = _super.user;
        this.weight = _super.weight;
    }

}


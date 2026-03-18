package org.mickleak.devtoolsexamples.domain.foo;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.mickleak.devtoolsexamples.domain.foo.vo.EmailVO;
import org.mickleak.devtoolsexamples.domain.foo.vo.FlagVO;
import org.mickleak.devtoolsexamples.domain.foo.vo.FooIdVo;
import org.mickleak.devtoolsexamples.domain.foo.vo.NameVO;


@SuppressWarnings( { "unused", "UnusedReturnValue" } )
@Builder
public class Foo {

	@Nullable
	@Getter
	private final FooIdVo id;

	@NonNull
	@Getter
	@Setter
	private NameVO  name;

	@NonNull
	@Getter
	@Setter
	private EmailVO email;

	@Nullable
	private FlagVO active;

	@Nullable
	private FlagVO verified; // can be true if and only if active is true


	public boolean canBeUsed() {
		return isActiveSet() && isVerifiedSet();
	}

	public boolean isActive() {
		return active != null && active.getValue();
	}

	public boolean isVerified() {
		return verified != null && verified.getValue();
	}

	public Foo deactivate() {
		if( !isActiveSet() ) throw new IllegalStateException( "already deactivated" );
		active = new FlagVO( false );
		verified = new FlagVO( false );
		return this;
	}

	public Foo activate() {
		if( isActiveSet() ) throw new IllegalStateException( "already active" );
		active = new FlagVO( true );
		verified = new FlagVO( false );
		return this;
	}

	public Foo setToVerified() {
		if( !isActiveSet() ) throw new IllegalStateException( "not active" );
		if( isVerifiedSet() ) throw new IllegalStateException( "already verified" );
		verified = new FlagVO( true );
		return this;
	}

	private boolean isActiveSet() {
		return active != null && active.getValue();
	}

	private boolean isVerifiedSet() {
		return verified != null && verified.getValue();
	}

	public boolean isVerifiedWithPossibleNullPointerException() {
//		return verified.getValue();  // static in the method + null-away
		return false;
	}
}

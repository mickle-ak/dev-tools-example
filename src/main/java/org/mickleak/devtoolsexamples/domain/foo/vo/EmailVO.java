package org.mickleak.devtoolsexamples.domain.foo.vo;

public final class EmailVO extends BaseVO<String>{
	public EmailVO( String value ) {
		super( value );
		validate();
	}

	private void validate() {
		// check email here
	}
}

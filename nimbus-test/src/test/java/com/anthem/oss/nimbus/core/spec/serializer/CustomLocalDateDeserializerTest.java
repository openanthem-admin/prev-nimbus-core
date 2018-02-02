/**
 *  Copyright 2016-2018 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.anthem.oss.nimbus.core.spec.serializer;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.antheminc.oss.nimbus.support.json.CustomLocalDateDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

/**
 * 
 * @author Tony Lopez (AF42192)
 *
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomLocalDateDeserializerTest {

	@InjectMocks
	private CustomLocalDateDeserializer testee;
	
	@Mock
	private JsonParser jsonParser;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void t1_deserialize() throws IOException {
		Mockito.when(this.jsonParser.getText()).thenReturn("1988-10-13");
		final LocalDate result = this.testee.deserialize(this.jsonParser, null);
		Mockito.verify(this.jsonParser, Mockito.times(1)).getText();
		Assert.assertEquals(LocalDate.of(1988, 10, 13), result);
	}
	
	@Test
	public void t2_deserializeDifferentFormat() throws IOException {
		Mockito.when(this.jsonParser.getText()).thenReturn("10/13/1988");
		final LocalDate result = this.testee.deserialize(this.jsonParser, null);
		Mockito.verify(this.jsonParser, Mockito.times(1)).getText();
		Assert.assertEquals(LocalDate.of(1988, 10, 13), result);
	}
	
	@Test
	public void t1_deserializeBadDate() throws IOException {
		Mockito.when(this.jsonParser.getText()).thenReturn("10-13-1988");
		this.thrown.expect(JsonParseException.class);
		this.thrown.expectMessage("Unparseable date: \"10-13-1988\". Supported formats: [yyyy-MM-dd, MM/dd/yyyy]");
		this.testee.deserialize(this.jsonParser, null);
	}
}
/*
 * Copyright (c) 2020 SvenAugustus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.flysium.io.remoting.codec;

import com.github.flysium.io.remoting.ProtocolMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sven Augustus
 * @version 1.0
 */
public class ProtocolMessageDecoderTest {

  @Test
  public void testDecode() throws Exception {
    EmbeddedChannel channel = new EmbeddedChannel(new ProtocolMessageDecoder());

    ProtocolMessage m1 = ProtocolMessage.newMessage((short) 0, "say hello".getBytes());
    ProtocolMessage m2 = ProtocolMessage.newMessage((short) 1, getBigString().getBytes());

    channel.writeInbound(encode(m1));
    channel.writeInbound(encode(m2));
    ProtocolMessage cm1 = channel.readInbound();
    ProtocolMessage cm2 = channel.readInbound();

    assertMessage(m1, cm1);
    assertMessage(m2, cm2);
  }

  protected String getBigString() {
    StringBuilder bigString = new StringBuilder(8096);
    for (int i = 0; i < 1024; i++) {
      bigString.append('s');
    }
    for (int i = 0; i < 2048; i++) {
      bigString.append('v');
    }
    for (int i = 0; i < 2048; i++) {
      bigString.append('e');
    }
    for (int i = 0; i < 3072; i++) {
      bigString.append('n');
    }
    return bigString.toString();
  }

  private ByteBuf encode(ProtocolMessage m1) throws Exception {
    ByteBuf out = Unpooled.buffer();
    byte[] body = m1.getBody();
    int length = body.length;
    out.writeShort(m1.getType());
    out.writeInt(length);
    out.writeBytes(body);
    return out.duplicate();
  }

  private void assertMessage(ProtocolMessage m1, ProtocolMessage cm1) {
    Assert.assertNotNull(cm1);
    Assert.assertEquals(m1.getType(), cm1.getType());
    Assert.assertEquals(m1.getLength(), cm1.getLength());
    Assert.assertArrayEquals(m1.getBody(), cm1.getBody());
    Assert.assertEquals(m1.getBody().length, cm1.getLength());
  }

}
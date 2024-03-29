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

package com.github.flysium.io.tank.service.objectfactory;

import com.github.flysium.io.tank.model.Bullet;
import com.github.flysium.io.tank.model.BulletAttributes;
import com.github.flysium.io.tank.model.DefaultBullet;
import com.github.flysium.io.tank.model.DefaultExplode;
import com.github.flysium.io.tank.model.DefaultTank;
import com.github.flysium.io.tank.model.Explode;
import com.github.flysium.io.tank.model.Group;
import com.github.flysium.io.tank.model.Tank;
import com.github.flysium.io.tank.model.TankAttributes;

/**
 * Default <code>GameObjectFactory</code>
 *
 * @author Sven Augustus
 * @version 1.0
 */
public class DefaultGameObjectFactory implements GameObjectFactory {

  @Override
  public Tank createTank(Group group, int x, int y, TankAttributes attributes) {
    return new DefaultTank(group, x, y, attributes);
  }

  @Override
  public Explode createExplode(Tank owner) {
    return new DefaultExplode(owner);
  }

  @Override
  public Bullet createBullet(Tank owner, BulletAttributes attributes) {
    return new DefaultBullet(owner, attributes);
  }

}

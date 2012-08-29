/**
 * Recess Maven Plugin
 * Copyright 2010-2011 (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

expected_from_css = """ul.user-graph > li .graph-bar {
  position: absolute;
  bottom: 23px;
  display: block;
  width: 100%;
  background: #ddd;
  border-radius: 2px;
}"""

expected_from_less = """#header {
  color: #4d926f;
}

h2 {
  color: #4d926f;
}"""

css = new File(basedir, "target/generated-sources/recess/test.css.css")
assert css.exists()
assert css.getText().equals(expected_from_css)

css = new File(basedir, "target/generated-sources/recess/test.less.css")
assert css.exists()
assert css.getText().equals(expected_from_less)
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

expected_another_file = """ul.user-graph>li .graph-bar{position:absolute;bottom:23px;display:block;width:100%;background:#ddd;border-radius:2px}"""

css = new File(basedir, "target/output-file-testing/another-file.css")
assert css.exists()
assert css.getText().equals(expected_another_file)

expected_test = """#header{color:#4d926f}h2{color:#4d926f}"""

css = new File(basedir, "target/output-file-testing/test.css")
assert css.exists()
assert css.getText().equals(expected_test)
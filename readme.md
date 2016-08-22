# Remote tailer
This project allow you to tail a fileset remotely.<br/>

<hr/>
<ul>
	<li><a href="#tailer-setup">Tailer setup</a></li>
	<li><a href="#execute-your-remote-tailer-and-enjoy-it-">Execute your remote-tailer and enjoy it :)</a></li>
</ul>
<hr/>

## Tailer Setup
<ol>
	<li>Clone this repo</li>
	<li>Choose a path where you want to put your configuration, and create it on your local machine (eg. /var/remote-tailer/my-cfg)</li>
	<li>Copy to your preferred path all "src/main/examples/" content (config.xml)</li>
	<li>
		Open "config.xml" (eg. /var/remote-tailer/my-cfg/config.xml) and apply your configuration, as the example below:
		
	<config>
		<tailer>
			<input type="FILE">
				<host>syslxlog1c</host>
				<port>22</port>
				<username>your_user</username>
				<password>your_pwd</password>
				<filePath>/your/path/file.log</filePath>
			</input>
			<output>
				<rollingFileAppender>
					<fileRootPath>/tmp/your/path</fileRootPath>
					<fileNamePattern>%d{yyyy-MM-dd}/file.%d{yyyy-MM-dd_HH}.log</fileNamePattern>
				</rollingFileAppender>
			</output>
		</tailer>	
	</config>
		
		<ul>
			<li>&lt;input&gt; is **required** node</li>
			<li>&lt;host&gt; is a **required** node where you put your remote host for ssh remote connection</li>
			<li>&lt;port&gt; is an **optional** node where you put your ssh remote port. The default value is **22**</li>
			<li>&lt;username&gt; is a **required** node where you put your credentials</li>
			<li>&lt;password&gt; is a **required** node where you put your credentials</li>
			<li>&lt;output&gt; is a **required** node where you put your remote file. Please take a look below for more details</li>
		</ul>
	</li>
</ol>

### output
**Restriction:** At this moment just a single output could be configured!

#### rollingFileAppender

| Param			        		| Type					| Description																							| Default		|
| ----------------------------- | ----------------------|-------------------------------------------------------------------------------------------------------|---------------|
| deleteOnStart					| attribute (boolean)	| Delete the root path on start																			| false			|
| layoutPattern					| attribute	(String)	| Using firefox browser instance																		| %msg			|
| fileRootPath					| node					| Based on ClassicPatternLayout (http://logback.qos.ch/manual/layouts.html#ClassicPatternLayout)		|				|
| fileNamePattern				| node					| Based on TimeBasedRollingPolicy (http://logback.qos.ch/manual/appenders.html#TimeBasedRollingPolicy)	|				|

## Execute your remote-tailer instance and enjoy it :)
You are ready to execute your remote-tailer.<br/>
Simple open your console (shell), point to your cloned repo (at pom.xml level) and call the command below:<br/>
<pre>
	<code>
mvn -f pom.xml compile exec:java -Dexec.mainClass=org.bitmarte.architecture.utils.remotetailer.Main -Dexec.args="/var/remote-tailer/my-cfg"
	</code>
</pre>
**Pay attention: specify your base config path as the only require java argument ("/var/remote-tailer/my-cfg" for this tutorial)**
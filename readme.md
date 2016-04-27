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
		<pre>
			<code>
&lt;config&gt;
	&lt;tailer&gt;
		&lt;input type="FILE"&gt;
			&lt;host&gt;yourHost&lt;/host&gt;
			&lt;port&gt;yourPort&lt;/port&gt;
			&lt;username&gt;username&lt;/username&gt;
			&lt;password&gt;password&lt;/password&gt;
			&lt;filePath&gt;/path/to/file.log&lt;/filePath&gt;
		&lt;/input&gt;
		&lt;output type="LOG_APPENDER"&gt;
			&lt;filePath&gt;/tmp/remote-tailer/%d{yyyy-MM-dd}\file.%d{yyyy-MM-dd_HH}.log&lt;/filePath&gt;
		&lt;/output&gt;
	&lt;/tailer&gt;
&lt;/config&gt;
			</code>
		</pre>
		<ul>
			<li>&lt;input&gt; is **require** node</li>
			<li>&lt;host&gt; is a **require** node where you put your remote host for ssh remote connection</li>
			<li>&lt;port&gt; is an **optional** node where you put your ssh remote port. The default value is **22**</li>
			<li>&lt;username&gt; is a **require** node where you put your credentials</li>
			<li>&lt;password&gt; is a **require** node where you put your credentials</li>
			<li>&lt;filePath&gt; is a **require** node where you put your remote filePath</li>
			<li>&lt;output&gt; is a **require** node</li>
			<li>&lt;filePath&gt; is a **require** node where you put your **FileNamePattern**, based on TimeBasedRollingPolicy (http://logback.qos.ch/manual/appenders.html#TimeBasedRollingPolicy)</li>
		</ul>
	</li>
</ol>

## Execute your remote-tailer instance and enjoy it :)
You are ready to execute your remote-tailer.<br/>
Simple open your console (shell), point to your cloned repo (at pom.xml level) and call the command below:<br/>
<pre>
	<code>
mvn -f pom.xml compile exec:java -Dexec.mainClass=org.bitmarte.architecture.utils.remotetailer.Main -Dexec.args="/var/remote-tailer/my-cfg"
	</code>
</pre>
**Pay attention: specify your base config path as the only require java argument ("/var/remote-tailer/my-cfg" for this tutorial)**
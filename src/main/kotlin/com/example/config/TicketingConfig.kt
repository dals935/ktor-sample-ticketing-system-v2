package com.example.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
import java.sql.DriverManager

class TicketingConfig {
    private val config = HikariConfig()
    private var ds: HikariDataSource? = null

    fun connect(): Connection {

        val url = "jdbc:postgresql://192.168.5.158:6432/postgres"
        val username = "postgres"
        val password = "root"

        config.jdbcUrl = url
        config.username = username
        config.password = password
        config.leakDetectionThreshold = 60000
        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "255")
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
        ds = HikariDataSource(config)

        return ds!!.connection

    }
}

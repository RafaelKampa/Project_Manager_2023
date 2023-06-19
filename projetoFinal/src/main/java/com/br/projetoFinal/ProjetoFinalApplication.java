package com.br.projetoFinal;

import com.br.projetoFinal.repositoryImpl.ServicoRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.transaction.*;

@SpringBootApplication
public class ProjetoFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoFinalApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public UserTransaction userTransaction() {
		return new UserTransaction() {
			@Override
			public void begin() throws NotSupportedException, SystemException {

			}

			@Override
			public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

			}

			@Override
			public void rollback() throws IllegalStateException, SecurityException, SystemException {

			}

			@Override
			public void setRollbackOnly() throws IllegalStateException, SystemException {

			}

			@Override
			public int getStatus() throws SystemException {
				return 0;
			}

			@Override
			public void setTransactionTimeout(int seconds) throws SystemException {

			}
		};
	}
}

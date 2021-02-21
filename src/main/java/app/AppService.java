package app;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class AppService {
	@Autowired
	private AppRepository repo;
	
	public List<Subscriptions> listAll() {//service function to find all subscription
		return repo.findAll();
	}
	public void save(Subscriptions subs) {//service function save to save in database
		repo.save(subs);
	}
	public void deleteSub(Integer id) {//service function delete by passed id
		repo.deleteById(id);
	}
	public Subscriptions get(Integer id) {//service function to find one by id
		return repo.findById(id).get();
	}
}

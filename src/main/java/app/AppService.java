package app;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class AppService {
	@Autowired
	private AppRepository repo;
	
	public List<Subscriptions> listAll() {
		return repo.findAll();
	}
	public void save(Subscriptions subs) {
		repo.save(subs);
	}
	public void deleteSub(Integer id) {
		repo.deleteById(id);
	}
	public Subscriptions get(Integer id) {
		return repo.findById(id).get();
	}
}

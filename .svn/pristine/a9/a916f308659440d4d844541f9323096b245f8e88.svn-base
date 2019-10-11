package com.thd.dataaccess.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.thd.dataaccess.model.Task;
import com.thd.dataaccess.util.impl.TaskManagerImpl;
import com.thd.dataaccess.util.impl.TransformModelImpl;

public class TransformModelForXmlTest extends TestCase{
	private TaskManager tm;
	private TransformModel tfm;
	private Map<String,Task> r;
	@Before
	public void setUp() throws Exception{
		tm = new TaskManagerImpl();
		tfm = new TransformModelImpl();
		File f = new File("E://thdsvn//java_project//data_access//xml//dsmis.xml");
		InputStream is = new FileInputStream(f);
		r = tfm.transformTaskToMap(is);
	}
	
	
	@Test
	public void test_ship_electric() throws Exception{
		tm.executeTask(r.get("ship_electric1"));
		tm.executeTask(r.get("ship_electric2"));
		tm.executeTask(r.get("ship_electric3"));
		tm.executeTask(r.get("ship_electric4"));
		tm.executeTask(r.get("ship_electric5"));
		tm.executeTask(r.get("ship_electric6"));
		tm.executeTask(r.get("ship_electric7"));
		tm.executeTask(r.get("ship_electric8"));
	}
	@Test
	public void test_deleteme01() throws Exception{
		Task task = r.get("test_deleteme01");
		System.out.println(task.getCustomSql());
		String sql = tm.taskToQuerySql(task);
		System.out.println(sql);
		tm.executeTask(task);
	}
	
	@Test
	public void test_deleteme02() throws Exception{
		Task task = r.get("test_deleteme02");
		System.out.println(task.getCustomSql());
		String sql = tm.taskToQuerySql(task);
		System.out.println(sql);
		tm.executeTask(task);
	}
	
	@Test
	public void test_deleteme03() throws Exception{
		Task task = r.get("test_deleteme03");
		System.out.println(task.getCustomSql());
		String sql = tm.taskToQuerySql(task);
		System.out.println(sql);
		tm.executeTask(task);
	}
	@Test
	public void test_deleteme04() throws Exception{
		Task task = r.get("test_deleteme04");
		System.out.println(task.getCustomSql());
		String sql = tm.taskToQuerySql(task);
		System.out.println(sql);
		tm.executeTask(task);
	}
	
	
	
	
	
	
	
	
	@Test
	public void test_ship_prevent_pollute() throws Exception{
		/*tm.executeTask(r.get("ship_prevent_pollute1"));
		tm.executeTask(r.get("ship_prevent_pollute2"));
		tm.executeTask(r.get("ship_prevent_pollute3"));
		tm.executeTask(r.get("ship_prevent_pollute4"));
		tm.executeTask(r.get("ship_prevent_pollute5"));
		tm.executeTask(r.get("ship_prevent_pollute6"));
		tm.executeTask(r.get("ship_prevent_pollute7"));
		tm.executeTask(r.get("ship_prevent_pollute8"));
		tm.executeTask(r.get("ship_prevent_pollute9"));
		tm.executeTask(r.get("ship_prevent_pollute10"));*/
		tm.executeTask(r.get("ship_prevent_pollute11"));
		tm.executeTask(r.get("ship_prevent_pollute12"));
	}
	
	/*
	@Test
	public void test_ship_notation() throws Exception{
		tm.executeTask(r.get("ship_notation"));
	}
	@Test
	public void test_ship_inclining() throws Exception{
		tm.executeTask(r.get("ship_inclining"));
	}
	@Test
	public void test_ship_sign_sound() throws Exception{
		tm.executeTask(r.get("ship_sign_sound"));
	}
	@Test
	public void test_ship_switchboard_emer() throws Exception{
		tm.executeTask(r.get("ship_switchboard_emer"));
	}
	@Test
	public void test_ship_hosesnozzles() throws Exception{
		tm.executeTask(r.get("ship_hosesnozzles"));
	}
	@Test
	public void test_ship_sign_flag() throws Exception{
		tm.executeTask(r.get("ship_sign_flag"));
	}
	@Test
	public void test_ship_pumps_cold() throws Exception{
		tm.executeTask(r.get("ship_pumps_cold"));
	}
	@Test
	public void test_ship_pumps_boiler() throws Exception{
		tm.executeTask(r.get("ship_pumps_boiler"));
	}
	@Test
	public void test_ship_mooringw() throws Exception{
		tm.executeTask(r.get("ship_mooringw"));
	}
	@Test
	public void test_ship_liferaft_down() throws Exception{
		tm.executeTask(r.get("ship_liferaft_down"));
	}
	@Test
	public void test_ship() throws Exception{
		tm.executeTask(r.get("ship1"));
		tm.executeTask(r.get("ship2"));
		tm.executeTask(r.get("ship3"));
		tm.executeTask(r.get("ship4"));
		tm.executeTask(r.get("ship5"));
		tm.executeTask(r.get("ship6"));
		tm.executeTask(r.get("ship7"));
		tm.executeTask(r.get("ship8"));
		tm.executeTask(r.get("ship9"));
		tm.executeTask(r.get("ship10"));
		tm.executeTask(r.get("ship11"));
		tm.executeTask(r.get("ship12"));
		tm.executeTask(r.get("ship13"));
	}
	@Test
	public void test_ship_certinfo_add() throws Exception{
		tm.executeTask(r.get("ship_certinfo_add1"));
		tm.executeTask(r.get("ship_certinfo_add2"));
		tm.executeTask(r.get("ship_certinfo_add3"));
		tm.executeTask(r.get("ship_certinfo_add4"));
		tm.executeTask(r.get("ship_certinfo_add5"));
		tm.executeTask(r.get("ship_certinfo_add6"));
		tm.executeTask(r.get("ship_certinfo_add7"));
	}
	@Test
	public void test_ship_phasesdate() throws Exception{
		tm.executeTask(r.get("ship_phasesdate1"));
		tm.executeTask(r.get("ship_phasesdate2"));
	}
	public void test_ship_cargo_soliddanger() throws Exception{
		tm.executeTask(r.get("ship_cargo_soliddanger"));
	}
	@Test
	public void test_ship_chainblock() throws Exception{
		tm.executeTask(r.get("ship_chainblock"));
	}
	@Test
	public void test_ship_part_size_material() throws Exception{
		tm.executeTask(r.get("ship_part_size_material"));
	}
	@Test
	public void test_ship_boiler() throws Exception{
		tm.executeTask(r.get("ship_boiler"));
	}
	@Test
	public void test_ship_pollute_cargo() throws Exception{
		tm.executeTask(r.get("ship_pollute_cargo"));
	}
	@Test
	public void test_ship_liferaft() throws Exception{
		tm.executeTask(r.get("ship_liferaft"));
	}
	@Test
	public void test_ship_lifeboat() throws Exception{
		tm.executeTask(r.get("ship_lifeboat"));
	}
	@Test
	public void test_ship_chainblock_info() throws Exception{
		tm.executeTask(r.get("ship_chainblock_info"));
	}
	@Test
	public void test_ship_prop() throws Exception{
		//tm.executeTask(r.get("ship_prop"));
		tm.executeTask(r.get("ship12"));
	}
	@Test
	public void test_ship_floor() throws Exception{
		tm.executeTask(r.get("ship_floor"));
	}
	
	@Test
	public void test_ship_steady() throws Exception{
		tm.executeTask(r.get("ship_steady1"));
		tm.executeTask(r.get("ship_steady2"));
		tm.executeTask(r.get("ship_steady3"));
		tm.executeTask(r.get("ship_steady4"));
		tm.executeTask(r.get("ship_steady5"));
		tm.executeTask(r.get("ship_steady6"));
		tm.executeTask(r.get("ship_steady7"));
		tm.executeTask(r.get("ship_steady8"));
		tm.executeTask(r.get("ship_steady9"));
		tm.executeTask(r.get("ship_steady10"));
		tm.executeTask(r.get("ship_steady11"));
	}
	@Test
	public void test_ship_struc() throws Exception{
		tm.executeTask(r.get("ship_struc1"));
		tm.executeTask(r.get("ship_struc2"));
	}
	@Test
	public void test_ship_tank_oil() throws Exception{
		tm.executeTask(r.get("ship_tank_oil1"));
		tm.executeTask(r.get("ship_tank_oil2"));
		tm.executeTask(r.get("ship_tank_oil3"));
	}
	@Test
	public void test_ship_roro_door() throws Exception{
		tm.executeTask(r.get("ship_roro_door1"));
		r.get("ship_roro_door1").getTargetService().execute("update p_ship_roro_door set door_type = '艏门' where door_type is null");
		tm.executeTask(r.get("ship_roro_door2"));
		r.get("ship_roro_door2").getTargetService().execute("update p_ship_roro_door set door_type = '艉门' where door_type is null");
	}
	@Test
	public void test_ship_pump_oil() throws Exception{
		tm.executeTask(r.get("ship_pump_oil"));
	}
	@Test
	public void test_ship_special_add_req() throws Exception{
		tm.executeTask(r.get("ship_special_add_req1"));
		tm.executeTask(r.get("ship_special_add_req2"));
	}
	@Test
	public void test_ship_wireless() throws Exception{
		tm.executeTask(r.get("ship_wireless"));
	}
	@Test
	public void test_ship_wireless_mark() throws Exception{
		tm.executeTask(r.get("ship_wireless_mark"));
	}
	@Test
	public void test_ship_wireless_other() throws Exception{
		tm.executeTask(r.get("ship_wireless_other"));
	}
	@Test
	public void test_ship_vhf() throws Exception{
		tm.executeTask(r.get("ship_vhf"));
	}
	
	@Test
	public void test_ship_save() throws Exception{
		tm.executeTask(r.get("ship_save"));
	}
	@Test
	public void test_ship_personal_save_life() throws Exception{
		tm.executeTask(r.get("ship_personal_save_life"));
	}
	
	@Test
	public void test_ship_lifeboat_down() throws Exception{
		tm.executeTask(r.get("ship_lifeboat_down"));
	}
	@Test
	public void test_ship_fire_move_tools() throws Exception{
		tm.executeTask(r.get("ship_fire_move_tools"));
	}
	@Test
	public void test_ship_special_add_req_tug() throws Exception{
		tm.executeTask(r.get("ship_special_add_req_tug"));
	}
	@Test
	public void test_ship_special_add_req_oil() throws Exception{
		tm.executeTask(r.get("ship_special_add_req_oil"));
	}
	
	@Test
	public void test_ship_tug_towline() throws Exception{
		tm.executeTask(r.get("ship_tug_towline"));
	}
	@Test
	public void test_ship_tug_equip() throws Exception{
		tm.executeTask(r.get("ship_tug_equip"));
	}
	@Test
	public void test_ship_tail_bearing() throws Exception{
		tm.executeTask(r.get("ship_tail_bearing"));
	}
	@Test
	public void test_ship_stern_tube() throws Exception{
		tm.executeTask(r.get("ship_stern_tube"));
	}
	@Test
	public void test_ship_special_req_fireboat() throws Exception{
		tm.executeTask(r.get("ship_special_req_fireboat"));
	}
	@Test
	public void test_ship_gen() throws Exception{
		tm.executeTask(r.get("ship_gen"));
	}
	@Test
	public void test_ship_gen_emer() throws Exception{
		tm.executeTask(r.get("ship_gen_emer1"));
		tm.executeTask(r.get("ship_gen_emer2"));
	}
	@Test
	public void test_ship_inert_gas_from() throws Exception{
		tm.executeTask(r.get("ship_inert_gas_from"));
	}
	@Test
	public void test_ship_propulsion() throws Exception{
		tm.executeTask(r.get("ship_propulsion"));
	}
	@Test
	public void test_ship_fixed_ballast() throws Exception{
		tm.executeTask(r.get("ship_fixed_ballast"));
	}
	@Test
	public void test_ship_fire_fightingother() throws Exception{
		tm.executeTask(r.get("ship_fire_fightingother"));
	}
	@Test
	public void test_ship_file() throws Exception{
		tm.executeTask(r.get("ship_file"));
	}
	@Test
	public void test_ship_exchange_boiler() throws Exception{
		tm.executeTask(r.get("ship_exchange_boiler"));
	}
	@Test
	public void test_ship_engine() throws Exception{
		tm.executeTask(r.get("ship_engine1"));
		tm.executeTask(r.get("ship_engine2"));
	}
	@Test
	public void test_ship_bilge_pumps() throws Exception{
		tm.executeTask(r.get("ship_bilge_pumps"));
	}
	
	@Test
	public void test_ship_cast() throws Exception{
		tm.executeTask(r.get("ship_cast"));
	}
	@Test
	public void test_ship_river_load_cargo() throws Exception{
		tm.executeTask(r.get("ship_river_load_cargo"));
	}
	@Test
	public void test_ship_air_press() throws Exception{
		tm.executeTask(r.get("ship_air_press"));
	}
	@Test
	public void test_ship_ballast_pumps() throws Exception{
		tm.executeTask(r.get("ship_ballast_pumps"));
	}
	
	
	
	
	
	
	@Test
	public void test_ship_rebuild() throws Exception{
		tm.executeTask(r.get("ship_rebuild1"));
		tm.executeTask(r.get("ship_rebuild2"));
	}
	@Test
	public void test_ship_foam_fire_sys() throws Exception{
		tm.executeTask(r.get("ship_foam_fire_sys"));
	}
	@Test
	public void test_ship_diesel() throws Exception{
		tm.executeTask(r.get("ship_diesel1"));
		tm.executeTask(r.get("ship_diesel2"));
		tm.executeTask(r.get("ship_diesel3"));
	}
	
	@Test
	public void test_ship_special_add_req_roro() throws Exception{
		tm.executeTask(r.get("ship_special_add_req_roro"));
	}
	@Test
	public void test_ship_special_req_float() throws Exception{
		tm.executeTask(r.get("ship_special_req_float"));
	}
	@Test
	public void test_ship_pumps_oil() throws Exception{
		tm.executeTask(r.get("ship_pumps_oil"));
	}
	@Test
	public void test_ship_room_eat() throws Exception{
		tm.executeTask(r.get("ship_room_eat"));
	}
	@Test
	public void test_ship_room_sleep() throws Exception{
		tm.executeTask(r.get("ship_room_sleep"));
	}
	
	@Test
	public void test_ship_sign_lamp() throws Exception{
		tm.executeTask(r.get("ship_sign_lamp"));
	}
	
	
	
	
	
	@Test
	public void test_ship_power_spare() throws Exception{
		tm.executeTask(r.get("ship_power_spare"));
	}
	@Test
	public void test_ship_pipe_oil() throws Exception{
		tm.executeTask(r.get("ship_pipe_oil"));
	}
	
	@Test
	public void test_ship_hydrants() throws Exception{
		tm.executeTask(r.get("ship_hydrants"));
	}
	
	@Test
	public void test_ship_fireextinguishers() throws Exception{
		tm.executeTask(r.get("ship_fireextinguishers"));
		
	}
	
	@Test
	public void test_ship_engine() throws Exception{
		tm.executeTask(r.get("ship_engine"));
	}
	@Test
	public void test_ship_direct_suction() throws Exception{
		tm.executeTask(r.get("ship_direct_suction"));
	}
	
	@Test
	public void test_ship_add_req_chemic() throws Exception{
		tm.executeTask(r.get("ship_add_req_chemic1"));
		tm.executeTask(r.get("ship_add_req_chemic2"));
	}
	
	@Test
	public void test_ship_accumulator() throws Exception{
		tm.executeTask(r.get("ship_accumulator1"));
		tm.executeTask(r.get("ship_accumulator2"));
	}
	
	@Test
	public void test_ship_special_add_req_gas() throws Exception{
		tm.executeTask(r.get("ship_special_add_req_gas1"));
		tm.executeTask(r.get("ship_special_add_req_gas2"));
		tm.executeTask(r.get("ship_special_add_req_gas3"));
		
	}
	
	@Test
	public void test_ship_fire_move() throws Exception{
		tm.executeTask(r.get("ship_fire_move"));
	}
	
	@Test
	public void test_ship_firemanoutfit() throws Exception{
		tm.executeTask(r.get("ship_firemanoutfit1"));
		tm.executeTask(r.get("ship_firemanoutfit2"));
	}
	@Test
	public void test_all() throws Exception{
		
		
		
		
		
		//导入数据不多 有可能出现问题的
//		
//		tm.executeTask(r.get("ship_room_funwork"));
//		tm.executeTask(r.get("ship_room_other"));
//		tm.executeTask(r.get("ship_roro_equip"));
//		tm.executeTask(r.get("ship_roro_lift_platform"));
//		tm.executeTask(r.get("ship_rud"));
//		tm.executeTask(r.get("ship_sailequip"));
//		tm.executeTask(r.get("ship_shaft"));
//		tm.executeTask(r.get("ship_sign_flashlight"));
//		tm.executeTask(r.get("ship_sign_type"));
//		tm.executeTask(r.get("ship_slow"));
//		
//		tm.executeTask(r.get("ship_special_equip"));
//		tm.executeTask(r.get("ship_sprinkler"));
//		tm.executeTask(r.get("ship_steering"));
//		
//		tm.executeTask(r.get("ship_tank_ballast"));
//		tm.executeTask(r.get("ship_tank_clear"));
//		tm.executeTask(r.get("ship_throw_line"));
//		tm.executeTask(r.get("ship_tightness"));
//		tm.executeTask(r.get("ship_valve"));
//		tm.executeTask(r.get("ship_water"));
//		tm.executeTask(r.get("ship_wind"));
//		tm.executeTask(r.get("ship_sprayinginstalla_se"));
//		tm.executeTask(r.get("ship_special_req_other"));
//		tm.executeTask(r.get("ship_personal_save_life"));
		
	}
	
	
	
	@Test
	public void test_ship_switchboard() throws Exception{
		tm.executeTask(r.get("ship_switchboard"));
	}
	
	
	
	@Test
	public void test_ship_co2installation() throws Exception{
		tm.executeTask(r.get("ship_co2installation"));
	}
	
	
	@Test
	public void test_ship_emergencycontrol() throws Exception{
		tm.executeTask(r.get("ship_emergencycontrol"));
	}
	@Test
	public void test_ship_passengers_info() throws Exception{
		tm.executeTask(r.get("ship_passengers_info"));
	}
	
	@Test
	public void test_ship_pollute_equip() throws Exception{
		tm.executeTask(r.get("ship_pollute_equip"));
	}
	
	
	@Test
	public void test_ship_ladder() throws Exception{
		tm.executeTask(r.get("ship_ladder"));
	}
	
	
	
	@Test
	public void test_ship_liquid_pipe_oil() throws Exception{
		tm.executeTask(r.get("ship_liquid_pipe_oil"));
	}
	
	@Test
	public void test_ship_nox() throws Exception{
		tm.executeTask(r.get("ship_nox"));
	}
	@Test
	public void test_ship_onfire() throws Exception{
		tm.executeTask(r.get("ship_onfire"));
	}
	@Test
	public void test_ship_ozone() throws Exception{
		tm.executeTask(r.get("ship_ozone"));
	}
	@Test
	public void test_ship_inert_gas() throws Exception{
		tm.executeTask(r.get("ship_inert_gas"));
		
	}
	@Test
	public void test_ship_inert_gas_attach() throws Exception{
		tm.executeTask(r.get("ship_inert_gas_attach"));
		
	}
	@Test
	public void test_ship_freeboard() throws Exception{
		tm.executeTask(r.get("ship_freeboard"));
	}
	@Test
	public void test_ship_freeboard_river() throws Exception{
		tm.executeTask(r.get("ship_freeboard_river"));
	}
	@Test
	public void test_ship_gasmeasurement() throws Exception{
		tm.executeTask(r.get("ship_gasmeasurement"));
	}
	@Test
	public void test_ship_gas_tank() throws Exception{
		tm.executeTask(r.get("ship_gas_tank"));
	}
	@Test
	public void test_ship_gas_tank2() throws Exception{
		tm.executeTask(r.get("ship_gas_tank2"));
	}
	@Test
	public void test_ship_gen() throws Exception{
		tm.executeTask(r.get("ship_gen"));
	}
	@Test
	public void test_ship_hold() throws Exception{
		tm.executeTask(r.get("ship_hold"));
	}
	@Test
	public void test_() throws Exception{
		tm.executeTask(r.get(""));
	}
	
	@Test
	public void test_ship_float_equip() throws Exception{
		tm.executeTask(r.get("ship_float_equip"));
	}
	
	@Test
	public void test_ship_firepumps() throws Exception{
		tm.executeTask(r.get("ship_firepumps"));
	}
	
	@Test
	public void test_ship_fireproof() throws Exception{
		tm.executeTask(r.get("ship_fireproof"));
	}
	@Test
	public void test_ship_firepump() throws Exception{
		tm.executeTask(r.get("ship_firepump"));
		
	}
	
	@Test
	public void test_ship_fired() throws Exception{
		tm.executeTask(r.get("ship_fired"));
	}
	
	@Test
	public void test_ship_fan() throws Exception{
		tm.executeTask(r.get("ship_fan"));
	}
	@Test
	public void test_ship_exchange_oil() throws Exception{
		tm.executeTask(r.get("ship_exchange_oil"));
	}
	@Test
	public void test_ship_exchange_cold() throws Exception{
		tm.executeTask(r.get("ship_exchange_cold"));
	}
	
	@Test
	public void test_ship_elec_push() throws Exception{
		tm.executeTask(r.get("ship_elec_push"));
	}
	@Test
	public void test_ship_detector() throws Exception{
		tm.executeTask(r.get("ship_detector"));
	}
	@Test
	public void test_ship_deckfoamsystem() throws Exception{
		tm.executeTask(r.get("ship_deckfoamsystem"));
	}
	@Test
	public void test_ship_controlpanels() throws Exception{
		tm.executeTask(r.get("ship_controlpanels"));
	}
	@Test
	public void test_ship_condenser() throws Exception{
		tm.executeTask(r.get("ship_condenser"));
	}
	@Test
	public void test_ship_compressor() throws Exception{
		tm.executeTask(r.get("ship_compressor"));
	}
	@Test
	public void test_ship_cmsainfo() throws Exception{
		tm.executeTask(r.get("ship_cmsainfo"));
	}
	@Test
	public void test_ship_chainblock_parts() throws Exception{
		tm.executeTask(r.get("ship_chainblock_parts"));
	}
	
	
	@Test
	public void test_ship_chain() throws Exception{
		tm.executeTask(r.get("ship_chain"));
	}
	
	
	
	@Test
	public void test_ship_cargo_listspa() throws Exception{
		tm.executeTask(r.get("ship_cargo_listspa"));
	}
	@Test
	public void test_ship_cargo_list() throws Exception{
		tm.executeTask(r.get("ship_cargo_list"));
	}
	@Test
	public void test_ship_cargo_detect_control() throws Exception{
		tm.executeTask(r.get("ship_cargo_detect_control"));
	}
	
	@Test
	public void test_ship_buoyant() throws Exception{
		tm.executeTask(r.get("ship_buoyant"));
	}
	@Test
	public void test_ship_breath_fac() throws Exception{
		tm.executeTask(r.get("ship_breath_fac"));
	}
	@Test
	public void test_ship_breaker_emer() throws Exception{
		tm.executeTask(r.get("ship_breaker_emer"));
	}
	
	@Test
	public void test_ship_breaker() throws Exception{
		tm.executeTask(r.get("ship_breaker"));
	}
	
	@Test
	public void test_ship_branch_suction() throws Exception{
		tm.executeTask(r.get("ship_branch_suction"));
	}
	
	
	
	
	@Test
	public void test_ship_axle_vibrate() throws Exception{
		//tm.executeTask(r.get("ship_axle_vibrate"));
	}
	
	@Test
	public void test_ship_axle() throws Exception{
		//tm.executeTask(r.get("ship_axle"));
	}
	@Test
	public void test_ship_antisepsis() throws Exception{
		//tm.executeTask(r.get("ship_antisepsis"));
	}
	@Test
	public void test_ship_anch() throws Exception{
		//tm.executeTask(r.get("ship_anch"));
	}
	
	
	
	
	@Test
	public void test_ship_air_bottle() throws Exception{
		//tm.executeTask(r.get("ship_air_bottle"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void test_ship_prop() throws Exception{
		//tm.executeTask(r.get("ship_prop"));
	}
	
	
	
	
	
	*/
	
	
}

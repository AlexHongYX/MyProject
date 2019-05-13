--根据指定的教学楼号+楼栋+楼层确定该层的所有教室
select classroom from buildlevel_classroom where buildlevel=2 and buildlevel_classroom_id in
(select buildlevel_classroom_id from classroom_all where build_buildnumber_id in
(select build_buildnumber_id from build_buildnumber where buildnumber='E' and build_id in
(select build_id from build where build='一号教学楼')))
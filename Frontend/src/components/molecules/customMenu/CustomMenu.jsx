import React, { useCallback, useEffect, useState } from 'react'
import PropTypes from 'prop-types';
import CustomTab from '../../atoms/customTab/CustomTab';
import './style.css'
import { Menu, Tab } from 'semantic-ui-react';
import BasicImage from '../../atoms/basicImage/BasicImage';

function CustomMenu({ data }) {

    const [tabs, setTabs] = useState([]);

    const loadData = useCallback(() => {
        const items = data.map((item) => {
            return {
                menuItem:
                    <Menu.Item key={item.title} className='menuTab default-tab-selected'>
                        {item.image && <BasicImage src={item.image} className='imageTab' size='mini' />}
                        <p>{item.title}</p>
                    </Menu.Item>,
                render: () =>
                    <Tab.Pane className='menuContent active default-tab-selected' >
                        {item.content}
                    </Tab.Pane>
            }
        });
        setTabs(items);
    }, [data])

    useEffect(() => {
        loadData();
    }, [loadData])

    return (
        <CustomTab
            panes={tabs}
            menu={{
                attached: 'top',
                className: 'menuNavBar'
            }}
        />
    );
}

CustomMenu.prototype = {
    data: PropTypes.array
}

CustomMenu.defaultProps = {
    data: []
}

export default CustomMenu;